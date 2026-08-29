package dev.pumpkin.shimgen;

import com.github.javaparser.StaticJavaParser;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Expands a {@link UsedSet} to include every {@code net.minecraft}/{@code net.neoforged}
 * supertype (superclass and interface, transitively) of the classes already in it.
 *
 * <p>A class whose parents are missing from the generated shim will not compile: {@code
 * class Player extends LivingEntity} needs a generated {@code LivingEntity} to exist,
 * whether or not the mods ever call anything on it directly. This pass runs before
 * anything is emitted so the emitted set is closed under "extends"/"implements".
 *
 * <p>Resolution here is deliberately syntactic rather than using JavaParser's symbol
 * solver: there is no classpath to give the solver, and for a decompiled tree every
 * type a class extends or implements is either imported, in the same package, or in
 * {@code java.lang} — so a name lookup against the compilation unit's own imports and
 * package is enough.
 */
public final class SupertypeCloser {
    private final List<Path> sourceRoots;
    private final Map<String, Optional<CompilationUnit>> cache = new HashMap<>();

    public SupertypeCloser(List<Path> sourceRoots) {
        this.sourceRoots = List.copyOf(sourceRoots);
    }

    /**
     * Parses {@code <root>/<internalName>.java} across the source roots in order,
     * returning the first match's {@link CompilationUnit}. Returns {@code null} when
     * no root has the file — normal for a class that will be hand-written instead of
     * decompiled, not an error. Cached by internal name: a later pass re-parses every
     * emitted class, and re-parsing hundreds of files twice would be wasted work.
     */
    public CompilationUnit parse(String internalName) {
        return cache.computeIfAbsent(internalName, this::doParse).orElse(null);
    }

    private Optional<CompilationUnit> doParse(String internalName) {
        for (Path root : sourceRoots) {
            Path file = root.resolve(internalName + ".java");
            if (Files.isRegularFile(file)) {
                try {
                    return Optional.of(StaticJavaParser.parse(file));
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Runs a worklist to fixpoint: for each class currently in {@code used}, parse it;
     * if there is no source, skip it (it will be hand-written); otherwise resolve its
     * primary type's {@code extends}/{@code implements} names and add any newly
     * resolved shimmed name to {@code used}. Repeats until a pass adds nothing.
     */
    public void close(UsedSet used) {
        Deque<String> worklist = new ArrayDeque<>(used.classes());
        while (!worklist.isEmpty()) {
            String internalName = worklist.removeFirst();
            CompilationUnit unit = parse(internalName);
            if (unit == null) {
                continue;
            }
            for (String superName : superTypeNames(unit)) {
                String resolved = resolve(unit, superName);
                String outer = Shimmed.outerOf(resolved);
                if (Shimmed.isShimmed(outer) && !used.classes().contains(outer)) {
                    used.addClass(outer, "supertype of " + internalName);
                    worklist.addLast(outer);
                }
            }
        }
    }

    /** The simple names in the primary type's {@code extends} and {@code implements} clauses. */
    private static List<String> superTypeNames(CompilationUnit unit) {
        List<String> names = new ArrayList<>();
        Optional<TypeDeclaration<?>> primary = unit.getPrimaryType();
        if (primary.isEmpty() || !primary.get().isClassOrInterfaceDeclaration()) {
            return names;
        }
        var decl = primary.get().asClassOrInterfaceDeclaration();
        for (ClassOrInterfaceType type : decl.getExtendedTypes()) {
            names.add(type.getNameAsString());
        }
        for (ClassOrInterfaceType type : decl.getImplementedTypes()) {
            names.add(type.getNameAsString());
        }
        return names;
    }

    /**
     * Resolves a simple type name to an internal name, in order: an import that ends
     * in {@code .<simpleName>} (a single-type import, never {@code .*}); then a known
     * {@code java.lang} simple name; then the compilation unit's own package; then
     * {@code java.lang} again as a last resort for anything left. In a tree this size
     * several simple names (e.g. {@code BlockPredicate}, {@code Main}) exist in
     * multiple packages; guessing beyond these rules risks silently binding to the
     * wrong one. A supertype that genuinely cannot be resolved this way should fail
     * loudly downstream (a missing class at {@code :shim:compileJava}) rather than be
     * silently mis-resolved here.
     *
     * <p>The {@code java.lang} check is placed before the package guess, not after:
     * unlike "does this file's own package contain a class of this name" (which this
     * method never actually verifies — there is no classpath to check it against, so
     * it is always just a guess), "is this simple name one of the fixed, small set of
     * public {@code java.lang} types" is exact, decidable knowledge that needs no
     * lookup at all. A decompiled file also essentially never imports {@code
     * java.lang} explicitly, so without checking it here, first, every unqualified
     * {@code String} or {@code Object} would be silently mis-resolved into this file's
     * own package by the guess below it.
     *
     * <p>Package-private, not private: {@link Pruner} faces the exact same
     * name-resolution problem for parameter and field types, and reuses this method
     * rather than carrying a second, independently-drifting guess at the same rules.
     */
    static String resolve(CompilationUnit unit, String simpleName) {
        for (ImportDeclaration imp : unit.getImports()) {
            if (imp.isStatic() || imp.isAsterisk()) {
                continue;
            }
            String qualified = imp.getNameAsString();
            if (qualified.endsWith("." + simpleName)) {
                return qualified.replace('.', '/');
            }
        }
        if (JAVA_LANG_SIMPLE_NAMES.contains(simpleName)) {
            return "java/lang/" + simpleName;
        }
        Optional<String> pkg = unit.getPackageDeclaration().map(pd -> pd.getNameAsString());
        if (pkg.isPresent()) {
            return pkg.get().replace('.', '/') + "/" + simpleName;
        }
        return "java/lang/" + simpleName;
    }

    /**
     * The public top-level {@code java.lang} simple names that plausibly appear
     * unqualified in Minecraft/NeoForge source: the common boxed types, the exception
     * and error hierarchy actually thrown/caught in game code, and the handful of
     * utility and reflection classes real decompiled source refers to by simple name.
     * Not exhaustive — {@code java.lang} has a fixed, small membership, but growing
     * this list only ever makes resolution more accurate, never less, so it is safe to
     * extend as a real miss turns up.
     */
    private static final Set<String> JAVA_LANG_SIMPLE_NAMES = Set.of(
            "Object", "String", "Integer", "Long", "Short", "Byte", "Double", "Float", "Boolean", "Character",
            "Void", "Number", "Comparable", "CharSequence", "Iterable", "Runnable", "Class", "ClassLoader", "Enum",
            "Record", "Throwable", "Exception", "RuntimeException", "Error", "StringBuilder", "StringBuffer",
            "Math", "StrictMath", "System", "Thread", "ThreadLocal", "ThreadGroup", "Cloneable", "AutoCloseable",
            "Deprecated", "Override", "SuppressWarnings", "FunctionalInterface", "SafeVarargs",
            "IllegalArgumentException", "IllegalStateException", "UnsupportedOperationException",
            "NullPointerException", "IndexOutOfBoundsException", "ArrayIndexOutOfBoundsException",
            "StringIndexOutOfBoundsException", "ArithmeticException", "ClassCastException",
            "NumberFormatException", "NegativeArraySizeException", "SecurityException", "InterruptedException",
            "CloneNotSupportedException", "StackOverflowError", "OutOfMemoryError", "AssertionError",
            "ExceptionInInitializerError", "NoSuchFieldException", "NoSuchMethodException", "ClassNotFoundException",
            "NoClassDefFoundError", "LinkageError", "VirtualMachineError", "Process", "ProcessBuilder", "Package",
            "Module", "Appendable", "Readable");
}
