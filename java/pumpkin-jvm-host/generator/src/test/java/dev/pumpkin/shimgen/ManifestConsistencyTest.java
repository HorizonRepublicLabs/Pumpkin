package dev.pumpkin.shimgen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;

/**
 * Holds the committed manifest and the committed source tree to each other, in both
 * directions.
 *
 * <p>These are the only checks in this plan that CI can run. Generation itself needs the
 * decompiled NeoForge tree and the two mod jars, neither of which is vendored, and the
 * linkage check needs the mod jars too -- so what CI can do is verify that the artifacts
 * already in the repository still agree. A manifest entry with no file is a class someone
 * has to hand-write and nobody has; a file with no manifest entry is an orphan left behind
 * by a class that dropped out of the used set, which still compiles and would otherwise
 * never show up in a diff.
 *
 * <p>Paths are relative to the {@code generator} project directory, which is Gradle's
 * working directory for its tests. {@link #manifest()} and {@link #shimRoot()} fail loudly
 * rather than silently passing an empty check if that ever stops being true.
 */
class ManifestConsistencyTest {
    private static final Path GENERATOR = Path.of("");
    private static final Path REPO = GENERATOR.toAbsolutePath().getParent();

    /**
     * Names in the manifest that no file backs and that must <em>not</em> be hand-written,
     * because they are not classes at all. Each is an artifact of {@link SupertypeCloser}
     * resolving a name syntactically, with no classpath and no symbol table:
     *
     * <ul>
     *   <li>{@code B}, {@code R} and the three {@code T}s are type <em>variables</em> --
     *       {@code LevelEntityGetter<T>}, {@code EntitySection<T>}, {@code
     *       DeferredHolder<R, T>}, {@code StateHolder<O, S>} -- read as if they were
     *       same-package class names.
     *   <li>{@code IContext}, {@code Kind}, both {@code Resolver}s, {@code
     *       PreparationBarrier} and {@code SharedState} are nested types written
     *       unqualified ({@code ICondition.IContext} is generated, as a nested interface
     *       of {@code ICondition}), which the same-package guess lifted to top level.
     * </ul>
     *
     * <p>Writing a top-level class for any of these would be actively wrong: it would
     * create a second, unrelated type under a name the real code uses for something else,
     * and the thing that actually needs to exist already does.
     */
    private static final Set<String> RESOLVER_ARTIFACTS = Set.of(
            "net/minecraft/world/level/block/state/T",
            "net/minecraft/world/level/entity/B",
            "net/minecraft/world/level/entity/T",
            "net/neoforged/neoforge/client/entity/animation/json/PreparationBarrier",
            "net/neoforged/neoforge/client/entity/animation/json/SharedState",
            "net/neoforged/neoforge/client/extensions/Resolver",
            "net/neoforged/neoforge/client/model/standalone/Resolver",
            "net/neoforged/neoforge/common/conditions/IContext",
            "net/neoforged/neoforge/registries/Kind",
            "net/neoforged/neoforge/registries/R",
            "net/neoforged/neoforge/registries/T");

    @Test
    void everyManifestClassHasAFileOrIsHandWrittenOrIsAResolverArtifact() throws IOException {
        SortedSet<String> handWritten = classesUnder(REPO.resolve("fml/src/main/java"));
        SortedSet<String> generated = classesUnder(shimRoot());

        SortedSet<String> unaccountedFor = new TreeSet<>();
        for (String internalName : manifestClasses()) {
            if (!generated.contains(internalName)
                    && !handWritten.contains(internalName)
                    && !RESOLVER_ARTIFACTS.contains(internalName)) {
                unaccountedFor.add(internalName);
            }
        }
        assertEquals(
                Set.of(),
                unaccountedFor,
                "manifest classes with no file in shim/, no hand-written file in fml/, and no entry on the"
                        + " documented resolver-artifact list. Each is a class a mod references and nothing"
                        + " supplies: either hand-write it under fml/src/main/java, or -- if it is a type"
                        + " variable or a nested type the resolver mis-placed -- add it to RESOLVER_ARTIFACTS"
                        + " with the reason");
    }

    @Test
    void everyGeneratedFileIsInTheManifest() throws IOException {
        SortedSet<String> manifestClasses = manifestClasses();
        SortedSet<String> orphans = new TreeSet<>();
        for (String internalName : classesUnder(shimRoot())) {
            if (!manifestClasses.contains(internalName)) {
                orphans.add(internalName);
            }
        }
        assertEquals(
                Set.of(),
                orphans,
                "files under shim/src/main/java that no manifest CLASS line names. Everything there is"
                        + " generated, so an orphan is a leftover from a class that has since dropped out of"
                        + " the used set. It still compiles, which is why nothing else catches it. Re-run"
                        + " ./regen.sh, which clears the generated roots before it writes");
    }

    @Test
    void theManifestIsSortedSoItsDiffIsReviewable() throws IOException {
        String text = Files.readString(manifest(), StandardCharsets.UTF_8);
        var classLines = text.lines().filter(l -> l.startsWith("CLASS\t")).toList();
        var memberLines = text.lines().filter(l -> l.startsWith("MEMBER\t")).toList();
        assertEquals(classLines.stream().sorted().toList(), classLines, "CLASS lines are not sorted");
        assertEquals(memberLines.stream().sorted().toList(), memberLines, "MEMBER lines are not sorted");
        assertTrue(classLines.size() + memberLines.size() == text.lines().count(),
                "the manifest holds a line that is neither CLASS nor MEMBER");
    }

    private static SortedSet<String> manifestClasses() throws IOException {
        try (Reader reader = Files.newBufferedReader(manifest(), StandardCharsets.UTF_8)) {
            return new TreeSet<>(UsedSet.readFrom(reader).classes());
        }
    }

    /** Every {@code .java} under {@code root}, as an internal name. */
    private static SortedSet<String> classesUnder(Path root) throws IOException {
        assertTrue(Files.isDirectory(root), root.toAbsolutePath() + " is not a directory");
        try (Stream<Path> files = Files.walk(root)) {
            SortedSet<String> names = new TreeSet<>();
            files.filter(p -> p.getFileName().toString().endsWith(".java"))
                    .forEach(p -> {
                        String relative = root.relativize(p).toString().replace('\\', '/');
                        names.add(relative.substring(0, relative.length() - ".java".length()));
                    });
            return names;
        }
    }

    private static Path manifest() {
        Path path = GENERATOR.resolve("used-set.txt");
        assertTrue(Files.isRegularFile(path),
                "the manifest is not at " + path.toAbsolutePath()
                        + "; this test assumes Gradle runs it with the generator project as its working"
                        + " directory");
        return path;
    }

    private static Path shimRoot() {
        return REPO.resolve("shim/src/main/java");
    }
}
