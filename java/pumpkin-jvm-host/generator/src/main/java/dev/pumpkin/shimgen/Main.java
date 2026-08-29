package dev.pumpkin.shimgen;

import com.github.javaparser.ast.CompilationUnit;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * The generator's entry point: reads the mod jars, closes the resulting {@link UsedSet}
 * over supertypes, writes the manifest, and emits one pruned Java source file per class.
 *
 * <p>Everything here iterates over sorted collections. Two runs over the same inputs must
 * produce a byte-identical manifest and a byte-identical file set, because both are
 * committed and reviewed as a diff; a {@code HashSet} anywhere on this path would churn
 * the diff on every regeneration and make review worthless.
 */
public final class Main {
    private Main() {}

    public static void main(String[] args) throws IOException {
        Args parsed = Args.parse(args);

        UsedSet used = new UsedSet();
        // One JarScanner call for all the jars, not one per jar: resolving an inherited
        // member walks the owner's supertype chain, and MysticalAgriculture's classes
        // extend Cucumber's, so the chains cross jar boundaries.
        JarScanner.scan(parsed.modJars, used);
        for (Path jar : parsed.modJars) {
            MixinScanner.scan(jar, used);
        }
        int seedClasses = used.classes().size();

        SupertypeCloser closer = new SupertypeCloser(parsed.sourceRoots);
        int closureRounds = close(closer, used, parsed.absentTypes);

        SortedSet<String> classes = used.classes();
        writeManifest(used, parsed.manifest);

        Set<String> inheritedAbstracts = abstractSignatures(closer, used);
        UsedSet keep = keepSet(closer, used);
        Pruner.resetReport();
        List<String> noSource = new ArrayList<>();
        int emittedShim = 0;
        int emittedFml = 0;
        for (String internalName : classes) {
            CompilationUnit cu = closer.parse(internalName);
            if (cu == null) {
                noSource.add(internalName);
                continue;
            }
            Pruner.prune(cu, internalName, keep, inheritedAbstracts, parsed.absentTypes);
            if (internalName.startsWith("net/neoforged/")) {
                Emitter.emit(cu, internalName, parsed.outFml);
                emittedFml++;
            } else {
                // Everything else Shimmed accepts is the game itself -- net/minecraft and
                // the three decompiled com/mojang packages -- and belongs in the shim.
                Emitter.emit(cu, internalName, parsed.outShim);
                emittedShim++;
            }
        }

        System.out.println("seed classes (from the mod jars):        " + seedClasses);
        System.out.println("classes after closure:                   " + classes.size());
        System.out.println("  added by closure:                      " + (classes.size() - seedClasses)
                + " over " + closureRounds + " rounds");
        System.out.println("members recorded:                        " + used.members().size());
        System.out.println("emitted:                                 " + (emittedShim + emittedFml)
                + " (shim " + emittedShim + ", fml " + emittedFml + ")");
        System.out.println("no source found:                         " + noSource.size());
        System.out.println();

        System.out.println("no source found (hand-write these):");
        for (String internalName : noSource) {
            System.out.println("  " + internalName);
        }
        System.out.println();

        System.out.println("source found but not parseable (" + closer.parseFailures().size()
                + ") -- hand-write these too, or upgrade the parser:");
        for (Map.Entry<String, String> failure : closer.parseFailures().entrySet()) {
            System.out.println("  " + failure.getKey() + "  " + failure.getValue());
        }
        System.out.println();

        SortedSet<String> dropped = Pruner.droppedForAbsentType();
        System.out.println("dropped because their signature names an --absent-type (" + dropped.size() + "):");
        for (String key : dropped) {
            System.out.println("  " + key);
        }
        System.out.println();

        printKeptByFallback();
        printMissingTypes(used);
    }

    /**
     * Closes {@code used} under both rules a generated file needs to compile, alternating
     * until neither adds anything: supertypes (a class cannot extend what was not
     * generated), and the types named in the signatures that survive pruning.
     *
     * <p>The second rule is not in the spec, whose measurement said signature closure adds
     * nothing. That measurement read erased JVM descriptors, where a kept {@code
     * List<Ingredient>} is just {@code List} -- so it could not see the type arguments,
     * {@code throws} clauses and stubbed field types that the emitted <em>source</em>
     * names and javac then demands a file for. Running the pruner over a clone of each
     * unit is what makes those visible; the clone matters, because the real unit must
     * reach the emitter unpruned by these throwaway passes.
     *
     * <p>Each round only ever adds classes, never members, so no member's keep/drop
     * decision changes underneath the next round, and the reports are recomputed from
     * scratch each time. Terminates because the source tree is finite and a class is added
     * at most once; the cap is there to fail loudly rather than spin if that stops holding.
     *
     * @return how many rounds ran
     */
    private static int close(SupertypeCloser closer, UsedSet used, Set<String> absentTypes) {
        int maxRounds = 50;
        for (int round = 1; round <= maxRounds; round++) {
            closer.close(used);
            Set<String> inheritedAbstracts = abstractSignatures(closer, used);
            // The clones are pruned against the keep set, not the used set, for the same
            // reason the real emission is: a member kept only because a subtype's call
            // site named it still has a signature, and that signature's types have to be
            // closed over or the emitted file will not compile.
            UsedSet keep = keepSet(closer, used);
            Pruner.resetReport();
            for (String internalName : used.classes()) {
                CompilationUnit cu = closer.parse(internalName);
                if (cu != null) {
                    Pruner.prune(cu.clone(), internalName, keep, inheritedAbstracts, absentTypes);
                }
            }
            SortedSet<String> missing = Pruner.missingTypesInKeptSignatures();
            if (missing.isEmpty()) {
                return round;
            }
            for (String internalName : missing) {
                used.addClass(internalName, "signature of a kept member");
            }
        }
        throw new IllegalStateException("closure did not reach a fixpoint in " + maxRounds + " rounds");
    }

    /**
     * The used set, plus every member re-filed under each shimmed supertype of its owner.
     * What {@link Pruner} decides against; never what gets written to the manifest.
     *
     * <p>A mod's call site names the class it was compiled against, not the class that
     * declares the member: {@code Player.getHealth()} is a {@code Methodref} on {@code
     * Player}, and {@code getHealth} is declared on {@code LivingEntity}. Pruning {@code
     * LivingEntity} against its own used-member set therefore deletes exactly the method
     * the mod calls, and the shim compiles perfectly while linking against nothing. The
     * linkage check found 124 of these in the two real mods -- {@code Player} alone
     * accounted for 30 -- and every one of them looked, from inside the generator, like a
     * member nobody wanted.
     *
     * <p>Re-filing rather than resolving: this does not work out which supertype declares
     * the member, it offers the same {@code name:descriptor} to all of them and lets
     * whichever one actually declares it match. Resolving properly would need overload
     * resolution across files with no classpath. Over-offering costs nothing -- a
     * supertype that does not declare the signature keeps nothing extra -- while
     * under-offering costs a mod that cannot link.
     *
     * <p>Kept out of the manifest deliberately. The manifest says what the mods reference,
     * and {@code LivingEntity.getInventory} is not something anything references; it is an
     * artifact of not knowing where {@code getInventory} lives. Writing these into the
     * committed file would multiply it several-fold with entries that are mostly phantom
     * and would make {@code Unimplemented}'s keys unjoinable against it.
     */
    private static UsedSet keepSet(SupertypeCloser closer, UsedSet used) {
        UsedSet keep = new UsedSet();
        for (String internalName : used.classes()) {
            keep.addClass(internalName, "used");
        }
        for (UsedSet.MemberRef ref : used.members()) {
            keep.addMember(ref, "used");
            for (String supertype : closer.supertypesOf(ref.owner())) {
                keep.addMember(new UsedSet.MemberRef(supertype, ref.name(), ref.descriptor()), "used");
            }
        }
        return keep;
    }

    /**
     * Every {@code name/arity} declared abstract anywhere in the set being generated. See
     * the four-argument {@link Pruner#prune} for why this is collected globally rather
     * than per supertype chain.
     */
    private static Set<String> abstractSignatures(SupertypeCloser closer, UsedSet used) {
        Set<String> signatures = new TreeSet<>();
        for (String internalName : used.classes()) {
            CompilationUnit cu = closer.parse(internalName);
            if (cu != null) {
                Pruner.collectAbstractSignatures(cu, signatures);
            }
        }
        return signatures;
    }

    /**
     * Prints {@link Pruner#keptByFallback()} split by member kind and grouped by owner.
     *
     * <p>Split by kind because the two halves mean different things and one drowns the
     * other: every constructor is kept unconditionally, so a constructor appears here
     * merely because the used set had no entry for it — normal, and high-volume. A field
     * or method appears here only because its exact {@code name:descriptor} missed and it
     * was kept on its name alone, which means this generator is not confident it
     * reproduced that descriptor, and the key baked into its emitted {@code
     * Unimplemented.forMember(...)} string may not be the key the runtime looks for.
     */
    private static void printKeptByFallback() {
        SortedSet<String> all = Pruner.keptByFallback();
        SortedMap<String, SortedSet<String>> constructors = new TreeMap<>();
        SortedMap<String, SortedSet<String>> byName = new TreeMap<>();
        for (String key : all) {
            String owner = key.substring(0, key.lastIndexOf('.', key.indexOf(':')));
            SortedMap<String, SortedSet<String>> target = key.contains(".<init>:") ? constructors : byName;
            target.computeIfAbsent(owner, k -> new TreeSet<>()).add(key);
        }
        System.out.println("kept by fallback -- fields and methods matched on name alone ("
                + count(byName) + " in " + byName.size() + " classes):");
        printGrouped(byName);
        System.out.println();
        System.out.println("kept by fallback -- constructors, kept unconditionally with no used-set entry ("
                + count(constructors) + " in " + constructors.size() + " classes):");
        printGrouped(constructors);
        System.out.println();
    }

    /**
     * Prints {@link Pruner#missingTypesInKeptSignatures()}, grouped by package.
     *
     * <p>This is the report that predicts compile failures. A type named in a kept
     * signature but absent from the used set was never generated, so the emitted source
     * references a class that has no file — a {@code :shim:compileJava} error whose
     * message points at the referencing class and says nothing about why the referenced
     * one is missing. Seeing it here, at generation time, is the whole point.
     */
    private static void printMissingTypes(UsedSet used) {
        SortedSet<String> missing = Pruner.missingTypesInKeptSignatures();
        SortedMap<String, SortedSet<String>> byPackage = new TreeMap<>();
        for (String internalName : missing) {
            int slash = internalName.lastIndexOf('/');
            String pkg = slash < 0 ? "" : internalName.substring(0, slash);
            byPackage.computeIfAbsent(pkg, k -> new TreeSet<>()).add(internalName);
        }
        System.out.println("types named in a kept signature but absent from the used set ("
                + missing.size() + " in " + byPackage.size() + " packages)");
        System.out.println("  -- these were never generated; each is a compile failure waiting to happen:");
        printGrouped(byPackage);
    }

    private static int count(SortedMap<String, SortedSet<String>> grouped) {
        int total = 0;
        for (SortedSet<String> group : grouped.values()) {
            total += group.size();
        }
        return total;
    }

    private static void printGrouped(SortedMap<String, SortedSet<String>> grouped) {
        for (Map.Entry<String, SortedSet<String>> entry : grouped.entrySet()) {
            System.out.println("  " + entry.getKey());
            for (String value : entry.getValue()) {
                System.out.println("    " + value);
            }
        }
    }

    private static void writeManifest(UsedSet used, Path manifest) throws IOException {
        Path parent = manifest.getParent();
        if (parent != null) {
            Files.createDirectories(parent);
        }
        try (Writer w = Files.newBufferedWriter(manifest, StandardCharsets.UTF_8)) {
            used.writeTo(w);
        }
    }

    /**
     * The command line. {@code --mc-sources} and {@code --neoforge-sources} may each be
     * repeated; the resulting roots are searched in the order given, which is the order
     * {@link SupertypeCloser} resolves a class against.
     *
     * <p>{@code --out-shim} and {@code --out-fml} may name the same directory, and in this
     * repository they do. The decompiled sources are NeoForge's patched Minecraft, in which
     * {@code net.minecraft} and {@code net.neoforged} reference each other; splitting them
     * across two Gradle projects would be a dependency cycle. The two flags stay separate
     * because the routing rule is real and a caller with an unpatched tree can still use
     * it.
     */
    private record Args(List<Path> sourceRoots, List<Path> modJars, Path outShim, Path outFml, Path manifest,
            Set<String> absentTypes) {
        static Args parse(String[] args) {
            List<Path> sourceRoots = new ArrayList<>();
            List<Path> modJars = new ArrayList<>();
            Set<String> absentTypes = new TreeSet<>();
            Path outShim = null;
            Path outFml = null;
            Path manifest = null;
            for (int i = 0; i < args.length; i++) {
                String flag = args[i];
                if (i + 1 >= args.length) {
                    throw new IllegalArgumentException("missing value for " + flag);
                }
                String value = args[++i];
                switch (flag) {
                    case "--mc-sources", "--neoforge-sources" -> sourceRoots.add(Path.of(value));
                    case "--mod-jar" -> modJars.add(Path.of(value));
                    case "--out-shim" -> outShim = Path.of(value);
                    case "--out-fml" -> outFml = Path.of(value);
                    case "--manifest" -> manifest = Path.of(value);
                    // A type the decompiled sources name but no published artifact
                    // provides: the game is built against a library build that was never
                    // released. Members naming one are dropped rather than emitted as
                    // source that cannot compile, and are listed at the end of the run.
                    case "--absent-type" -> absentTypes.add(value.replace('.', '/'));
                    default -> throw new IllegalArgumentException("unknown flag: " + flag);
                }
            }
            require(!sourceRoots.isEmpty(), "--mc-sources");
            require(!modJars.isEmpty(), "--mod-jar");
            require(outShim != null, "--out-shim");
            require(outFml != null, "--out-fml");
            require(manifest != null, "--manifest");
            for (Path root : sourceRoots) {
                if (!Files.isDirectory(root)) {
                    throw new IllegalArgumentException("source root is not a directory: " + root);
                }
            }
            for (Path jar : modJars) {
                if (!Files.isRegularFile(jar)) {
                    throw new IllegalArgumentException("mod jar is not a file: " + jar);
                }
            }
            return new Args(sourceRoots, modJars, outShim, outFml, manifest, absentTypes);
        }

        private static void require(boolean condition, String flag) {
            if (!condition) {
                throw new IllegalArgumentException("missing required flag: " + flag);
            }
        }
    }
}
