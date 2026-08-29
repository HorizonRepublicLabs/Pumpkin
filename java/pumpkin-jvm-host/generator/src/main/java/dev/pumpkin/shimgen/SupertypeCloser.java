package dev.pumpkin.shimgen;

import com.github.javaparser.JavaParser;
import com.github.javaparser.ParseResult;
import com.github.javaparser.ParserConfiguration;
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
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

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
    private final SortedMap<String, String> parseFailures = new TreeMap<>();

    /**
     * A parser of its own rather than {@link com.github.javaparser.StaticJavaParser},
     * configured for the language level the decompiled tree is actually written in.
     * JavaParser defaults to an older level at which a plain {@code record} declaration
     * or an {@code instanceof} pattern is a parse error, and modern Minecraft source is
     * full of both; a shared static configuration would also mean this class silently
     * reconfiguring every other parse in the JVM, tests included.
     *
     * <p>Three parsers, tried in order, because no single level parses the whole tree.
     * {@code JAVA_25} is needed for flexible constructor bodies ({@code
     * CrashReportCategory}, {@code CubicSpline}) and unnamed variables ({@code
     * CommonHooks}), but JavaParser 3.28.0's {@code JAVA_25} grammar regresses on {@code
     * yield} inside a switch expression ({@code Screen}, {@code FilterMask}, {@code
     * NoiseChunk}) and on {@code Util}, all of which {@code JAVA_24} handles. Falling back
     * costs a second parse of a handful of files and is strictly better than either level
     * alone; each of these files is named by other generated ones, so losing any of them
     * fails the compile.
     */
    private static final List<ParserConfiguration.LanguageLevel> LEVELS = List.of(
            ParserConfiguration.LanguageLevel.JAVA_25,
            ParserConfiguration.LanguageLevel.JAVA_24,
            ParserConfiguration.LanguageLevel.JAVA_21);

    private final List<JavaParser> parsers = LEVELS.stream()
            .map(level -> new JavaParser(new ParserConfiguration().setLanguageLevel(level)))
            .toList();

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
                    ParseResult<CompilationUnit> result = null;
                    for (JavaParser parser : parsers) {
                        result = parser.parse(file);
                        if (result.isSuccessful() && result.getResult().isPresent()) {
                            break;
                        }
                    }
                    if (!result.isSuccessful() || result.getResult().isEmpty()) {
                        // A partially-parsed unit is worse than none: JavaParser still
                        // hands back a CompilationUnit, and printing it emits "???" --
                        // a file that is not Java at all, which then stops javac before
                        // it reports a single real problem anywhere else in the tree.
                        // Treat it as sourceless and make the reason visible instead.
                        parseFailures.put(internalName, String.valueOf(result.getProblems()));
                        return Optional.empty();
                    }
                    return Optional.of(result.getResult().get());
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }
        }
        return Optional.empty();
    }

    /**
     * Every class whose source file exists but could not be parsed, mapped to the parse
     * problems. Each is a class this generator cannot produce and someone must hand-write
     * -- or a signal that the language has moved past the parser and it needs upgrading.
     */
    public SortedMap<String, String> parseFailures() {
        return java.util.Collections.unmodifiableSortedMap(parseFailures);
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
                String resolved = resolveScoped(unit, superName);
                String outer = Shimmed.outerOf(resolved);
                if (Shimmed.isShimmed(outer) && !used.classes().contains(outer)) {
                    used.addClass(outer, "supertype of " + internalName);
                    worklist.addLast(outer);
                }
            }
        }
    }

    /**
     * Every shimmed supertype of {@code internalName}, transitively, superclasses and
     * interfaces alike, nested types included. Empty when the class has no source.
     *
     * <p>{@link #close} answers a different question -- which classes must exist -- and
     * only ever looks at a file's primary type. This one is asked about a <em>member</em>
     * owner, which is routinely a nested type ({@code BlockBehaviour$BlockStateBase},
     * {@code Item$Properties}), so it resolves the declaration inside the file rather
     * than assuming the primary one.
     *
     * <p>Why anything needs it: a mod's call site names the owner it was compiled against,
     * not the class that declares the method. {@code Player.getHealth()} is a
     * {@code Methodref} on {@code Player}, but {@code getHealth} is declared on {@code
     * LivingEntity}, so pruning {@code LivingEntity} against its own used-member set
     * deletes the very method the mod calls, and the shim links against nothing. See
     * {@link Main#keepSet}.
     */
    public SortedSet<String> supertypesOf(String internalName) {
        SortedSet<String> found = new TreeSet<>();
        collectSupertypes(internalName, found);
        return found;
    }

    private void collectSupertypes(String internalName, SortedSet<String> found) {
        CompilationUnit unit = parse(Shimmed.outerOf(internalName));
        if (unit == null) {
            return;
        }
        TypeDeclaration<?> decl = typeDeclarationOf(unit, internalName);
        if (decl == null || !decl.isClassOrInterfaceDeclaration()) {
            return;
        }
        var classOrInterface = decl.asClassOrInterfaceDeclaration();
        List<ClassOrInterfaceType> supertypes = new ArrayList<>(classOrInterface.getExtendedTypes());
        supertypes.addAll(classOrInterface.getImplementedTypes());
        for (ClassOrInterfaceType supertype : supertypes) {
            String resolved = resolveScoped(unit, supertype.getNameWithScope());
            if (!Shimmed.isShimmed(Shimmed.outerOf(resolved))) {
                continue;
            }
            // found.add is the cycle guard as well as the result: a name already seen has
            // already had its own supertypes walked.
            if (found.add(resolved)) {
                collectSupertypes(resolved, found);
            }
        }
    }

    /**
     * The declaration {@code internalName} names inside {@code unit}: the top-level type
     * whose name matches, then one nested member type per {@code $} segment. {@code null}
     * when the file does not declare it, which happens whenever a nested supertype name
     * was written unqualified and {@link #resolve}'s same-package guess turned it into a
     * top-level name that exists nowhere.
     */
    private static TypeDeclaration<?> typeDeclarationOf(CompilationUnit unit, String internalName) {
        String[] segments = internalName.substring(internalName.lastIndexOf('/') + 1).split("\\$");
        TypeDeclaration<?> current = null;
        for (TypeDeclaration<?> type : unit.getTypes()) {
            if (type.getNameAsString().equals(segments[0])) {
                current = type;
                break;
            }
        }
        for (int i = 1; i < segments.length && current != null; i++) {
            TypeDeclaration<?> next = null;
            for (var member : current.getMembers()) {
                if (member instanceof TypeDeclaration<?> nested && nested.getNameAsString().equals(segments[i])) {
                    next = nested;
                    break;
                }
            }
            current = next;
        }
        return current;
    }

    /**
     * The names in the primary type's {@code extends} and {@code implements} clauses,
     * each with whatever scope it was written with -- {@code BlockBehaviour.BlockStateBase},
     * not {@code BlockStateBase}.
     *
     * <p>Dropping the scope was wrong, and wrong in a way that hid itself: {@code
     * BlockStateBase} alone resolves by the same-package guess to the top-level name
     * {@code net/minecraft/world/level/block/state/BlockStateBase}, which does not exist,
     * so it silently became a "no source found" entry -- a class someone would then be
     * asked to hand-write, when the real supertype is a nested class of a file already in
     * the set.
     */
    private static List<String> superTypeNames(CompilationUnit unit) {
        List<String> names = new ArrayList<>();
        Optional<TypeDeclaration<?>> primary = unit.getPrimaryType();
        if (primary.isEmpty() || !primary.get().isClassOrInterfaceDeclaration()) {
            return names;
        }
        var decl = primary.get().asClassOrInterfaceDeclaration();
        for (ClassOrInterfaceType type : decl.getExtendedTypes()) {
            names.add(type.getNameWithScope());
        }
        for (ClassOrInterfaceType type : decl.getImplementedTypes()) {
            names.add(type.getNameWithScope());
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
                return internalNameOf(qualified);
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
     * Resolves a possibly-scoped type name -- {@code BlockStateBase}, {@code
     * BlockBehaviour.BlockStateBase}, or {@code net.minecraft.core.HolderLookup.Provider}
     * -- to an internal name, nested types joined with {@code $}.
     *
     * <p>A leading run of segments starting with a lower-case letter is a package; the
     * first upper-case segment is the top-level class and everything after it is nested.
     * That rule is a convention, not a language guarantee, but the input here is one
     * decompiler's output over one code base, where it holds without exception. When
     * there is no package prefix the leading segment goes through {@link #resolve}, so an
     * import or the enclosing package still gets its say.
     */
    static String resolveScoped(CompilationUnit unit, String dottedName) {
        String[] segments = dottedName.split("\\.");
        if (segments.length == 1 || Character.isLowerCase(segments[0].charAt(0))) {
            return segments.length == 1 ? resolve(unit, segments[0]) : internalNameOf(dottedName);
        }
        StringBuilder sb = new StringBuilder(resolve(unit, segments[0]));
        for (int i = 1; i < segments.length; i++) {
            sb.append('$').append(segments[i]);
        }
        return sb.toString();
    }

    /**
     * Turns a fully-qualified dotted name into an internal name, separating package
     * segments with {@code /} and nested-class segments with {@code $} by the same
     * leading-lower-case-is-a-package rule {@link #resolveScoped} uses. {@code
     * java.util.Map.Entry} becomes {@code java/util/Map$Entry}; a plain {@code
     * String.replace('.', '/')} would produce {@code java/util/Map/Entry}, whose outer
     * class is the whole thing and which therefore has no source anywhere.
     */
    static String internalNameOf(String dottedName) {
        StringBuilder sb = new StringBuilder();
        boolean inClass = false;
        for (String segment : dottedName.split("\\.")) {
            if (sb.length() > 0) {
                sb.append(inClass ? '$' : '/');
            }
            sb.append(segment);
            inClass = inClass || (!segment.isEmpty() && Character.isUpperCase(segment.charAt(0)));
        }
        return sb.toString();
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
