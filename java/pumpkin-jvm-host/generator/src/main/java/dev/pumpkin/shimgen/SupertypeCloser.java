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
                if (resolved != null && isShimmed(resolved) && !used.classes().contains(resolved)) {
                    used.addClass(resolved, "supertype of " + internalName);
                    worklist.addLast(resolved);
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
     * in {@code .<simpleName>} (a single-type import, never {@code .*}); then the
     * compilation unit's own package, walking up through each enclosing package
     * (checked by actually finding a source file there, via {@link #parse}) so a
     * same-tree reference one or more packages up still resolves without an import;
     * then {@code java.lang} as an unverified last resort.
     *
     * <p>The package walk-up is a deliberate widening of "own package": a decompiled
     * tree is expected to always import a cross-package reference, but resolution
     * must not silently guess a wrong (non-existent) internal name when it doesn't —
     * checking existence via {@code parse} at each level means this only ever returns
     * a name that is backed by a real file, never a plausible-looking miss.
     */
    private String resolve(CompilationUnit unit, String simpleName) {
        for (ImportDeclaration imp : unit.getImports()) {
            if (imp.isStatic() || imp.isAsterisk()) {
                continue;
            }
            String qualified = imp.getNameAsString();
            if (qualified.endsWith("." + simpleName)) {
                return qualified.replace('.', '/');
            }
        }
        String pkg = unit.getPackageDeclaration().map(pd -> pd.getNameAsString()).orElse("");
        String[] segments = pkg.isEmpty() ? new String[0] : pkg.split("\\.");
        for (int depth = segments.length; depth >= 0; depth--) {
            StringBuilder path = new StringBuilder();
            for (int i = 0; i < depth; i++) {
                path.append(segments[i]).append('/');
            }
            String candidate = path + simpleName;
            if (parse(candidate) != null) {
                return candidate;
            }
        }
        return "java/lang/" + simpleName;
    }

    /**
     * Strips {@code $} and everything after it, so a nested type's reference lands on
     * the outer class the generator actually emits a file for. Mirrors {@link
     * JarScanner}'s and {@link MixinScanner}'s convention exactly.
     */
    private static String outerOf(String internalName) {
        int dollar = internalName.indexOf('$');
        return dollar < 0 ? internalName : internalName.substring(0, dollar);
    }

    /** Only these packages are shimmed; mirrors {@code JarScanner}'s filter exactly. */
    private static boolean isShimmed(String internalName) {
        String outer = outerOf(internalName);
        return outer.startsWith("net/minecraft/") || outer.startsWith("net/neoforged/");
    }
}
