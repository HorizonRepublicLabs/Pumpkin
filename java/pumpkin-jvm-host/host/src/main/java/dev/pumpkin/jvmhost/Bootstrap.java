package dev.pumpkin.jvmhost;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import dev.pumpkin.shim.PumpkinEventBus;
import dev.pumpkin.shim.Unimplemented;
import dev.pumpkin.shim.PumpkinModContainer;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.RegisterEvent;

/** The entry point Rust calls to bring a mod up. */
public final class Bootstrap {
    private Bootstrap() {
    }

    /**
     * Points {@link DeferredRegister}'s registration sink at Pumpkin.
     *
     * <p>{@link DeferredRegister}'s default sink throws, so without this a mod's registrations
     * would fail instead of reaching Pumpkin. This is deliberately not called from {@link
     * #loadAndRegister}: doing so would reinstall the production sink (which calls the native
     * {@link PumpkinHost#registerBlock}) over any sink a test installs, breaking tests that run
     * without a native library bound. Rust calls this once during boot, before loading any mod.
     */
    public static void installDefaultSink() {
        DeferredRegister.setSink(new DeferredRegister.Sink() {
            @Override
            public int registerBlock(String id, String template) {
                return PumpkinHost.registerBlock(id, template);
            }

            // The wide path. Nulls become NaN, the native's "the mod did not say".
            @Override
            public int registerBlock(String id, String template, Float destroyTime,
                    Float explosionResistance, boolean requiresTool) {
                return PumpkinHost.registerBlockWithProperties(id, template,
                        destroyTime == null ? Float.NaN : destroyTime,
                        explosionResistance == null ? Float.NaN : explosionResistance,
                        requiresTool);
            }

            @Override
            public int registerItem(String id, String template) {
                return PumpkinHost.registerItem(id, template);
            }

            @Override
            public int registerItem(String id, String template, int maxStackSize,
                    int maxDamage, String blockId) {
                return PumpkinHost.registerItemWithProperties(id, template, maxStackSize,
                        maxDamage, blockId);
            }

            @Override
            public int registerBlockEntityType(String id) {
                return PumpkinHost.registerBlockEntityType(id);
            }

            @Override
            public int registerMenuType(String id) {
                return PumpkinHost.registerMenuType(id);
            }

            @Override
            public int registerSoundEvent(String id) {
                return PumpkinHost.registerSoundEvent(id);
            }

            @Override
            public int registerDataComponentType(String id) {
                return PumpkinHost.registerDataComponentType(id);
            }
        });
    }

    /**
     * Reads a mod jar's declared id without constructing anything from it.
     *
     * <p>Pumpkin's plugin manager builds a plugin's metadata from this before deciding
     * whether the plugin is even allowed to run - a config override can disable it, and a
     * permission check can deny it, both after this returns and before {@link
     * #loadAndRegister} is ever called. This must not run any of the mod's code, so it
     * stops at {@link ModLoader#discover}, which finds the {@code @Mod} class via {@link
     * Class#forName(String, boolean, ClassLoader) Class.forName(name, false, loader)} -
     * {@code initialize=false} - rather than constructing it.
     *
     * @param jarPath absolute path to the mod jar
     * @return the mod id declared in the jar's {@code neoforge.mods.toml}
     * @throws java.io.IOException if the jar cannot be read
     */
    public static String discoverModId(String jarPath) throws java.io.IOException {
        return ModLoader.discover(Path.of(jarPath)).modId();
    }

    /**
     * Loads a mod jar, constructs its {@code @Mod} class, and fires {@link RegisterEvent}.
     *
     * <p>This is where the mod's own code actually runs, and it re-discovers the jar rather
     * than reusing whatever {@link #discoverModId} found: the two calls are independent, and
     * neither is guaranteed to have run before the other.
     *
     * @param jarPath absolute path to the mod jar
     * @return the mod id that was loaded
     * @throws Exception if discovery, construction or registration failed; Rust turns this
     *                   into a loader error
     */
    public static String loadAndRegister(String jarPath) throws Exception {
        ModLoader.ModCandidate candidate = ModLoader.discover(Path.of(jarPath));
        IEventBus bus = new PumpkinEventBus();

        Constructor<?> constructor = injectableConstructor(candidate.mainClass());
        try {
            constructor.newInstance(argumentsFor(constructor, bus, candidate.modId()));
        } catch (InvocationTargetException e) {
            // newInstance wraps whatever the mod's constructor threw. Unwrapped, that surfaces
            // as a bare InvocationTargetException naming neither the mod nor the jar, leaving
            // the actual cause reachable only via getCause(). Name both here and keep the real
            // cause attached, since that is what the person reading this trace is looking for.
            Throwable cause = e.getCause() != null ? e.getCause() : e;
            throw new Exception(
                    "mod " + candidate.modId() + " (" + candidate.mainClass().getName()
                            + ") failed to construct from " + jarPath + ": " + cause,
                    cause);
        }

        bus.post(new RegisterEvent());
        // Told after construction, not before: a mod that failed to construct is not loaded,
        // and claiming otherwise would have the next mod take an integration path against
        // something that is not there.
        ModList.pumpkinMarkLoaded(candidate.modId(), Path.of(jarPath));
        return candidate.modId();
    }

    /**
     * Picks the constructor NeoForge would call.
     *
     * <p>NeoForge injects a mod constructor's arguments <em>by type</em> rather than
     * requiring a fixed signature, so assuming one is wrong: of the two real mods measured,
     * both declare {@code (IEventBus, ModContainer)} and neither declares {@code
     * (IEventBus)}. Assuming the latter is what made both fail to construct with a
     * {@code NoSuchMethodException} naming a constructor they never had.
     *
     * <p>Prefers the constructor with the most parameters it can supply, so a mod asking for
     * more context gets it rather than silently taking a barer overload.
     *
     * @throws NoSuchMethodException if no declared constructor takes only injectable types,
     *                               naming what the mod asked for
     */
    private static Constructor<?> injectableConstructor(Class<?> modClass)
            throws NoSuchMethodException {
        Constructor<?> best = null;
        for (Constructor<?> candidate : modClass.getConstructors()) {
            boolean injectable = true;
            for (Class<?> parameter : candidate.getParameterTypes()) {
                if (!isInjectable(parameter)) {
                    injectable = false;
                    break;
                }
            }
            if (injectable
                    && (best == null
                            || candidate.getParameterCount() > best.getParameterCount())) {
                best = candidate;
            }
        }
        if (best == null) {
            StringBuilder asked = new StringBuilder();
            for (Constructor<?> candidate : modClass.getConstructors()) {
                asked.append("\n  ").append(candidate);
            }
            throw new NoSuchMethodException(
                    modClass.getName()
                            + " declares no constructor this host can supply. Injectable types"
                            + " are IEventBus, ModContainer and Dist. It declares:"
                            + asked);
        }
        return best;
    }

    private static boolean isInjectable(Class<?> parameter) {
        return parameter == IEventBus.class
                || parameter == ModContainer.class
                || parameter == Dist.class;
    }

    /** Builds the argument list for a constructor {@link #injectableConstructor} accepted. */
    private static Object[] argumentsFor(Constructor<?> constructor, IEventBus bus, String modId) {
        Class<?>[] parameters = constructor.getParameterTypes();
        Object[] arguments = new Object[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            if (parameters[i] == IEventBus.class) {
                arguments[i] = bus;
            } else if (parameters[i] == ModContainer.class) {
                arguments[i] = new PumpkinModContainer(modId);
            } else {
                // Dist. Pumpkin is a dedicated server; this is a fact about it, not a
                // placeholder, which is why it answers rather than throwing.
                arguments[i] = Dist.DEDICATED_SERVER;
            }
        }
        return arguments;
    }

    /**
     * Every stubbed member reached so far in this JVM, newline-separated, sorted.
     *
     * <p>Rust calls this after loading every mod. A mod that throws stops at its first
     * missing member, so no single boot enumerates everything it needs -- but hits
     * accumulate across all mods in the run, and each entry is a manifest key, so the
     * result is a worklist rather than a stack trace to read by eye.
     *
     * <p>Empty is the interesting answer: it means nothing reached a stub, and whatever went
     * wrong was not a missing shim member.
     */
    public static String burndown() {
        return String.join("\n", new java.util.TreeSet<>(Unimplemented.hits()));
    }

    /**
     * Extracts a mod jar's {@code data/} tree into a datapack directory.
     *
     * <p>A NeoForge jar carries its recipes, loot tables and tags as an ordinary datapack
     * under {@code data/}; Pumpkin's datapack loader reads directories, so the tree is
     * copied out rather than served from the zip. The copy is skipped when the target is
     * newer than the jar, so a server that boots twice does the work once.
     *
     * @param jarPath   the mod jar
     * @param targetDir the datapack directory to create, e.g. {@code
     *                  world/datapacks/mod_examplemod}
     * @return the number of files extracted; 0 means the target was already current
     * @throws Exception if the jar cannot be read or a file cannot be written
     */
    public static int extractDatapack(String jarPath, String targetDir) throws Exception {
        Path jar = Path.of(jarPath);
        Path target = Path.of(targetDir);
        Path marker = target.resolve(".jar-modified-time");

        long jarTime = java.nio.file.Files.getLastModifiedTime(jar).toMillis();
        // First line: the jar's mtime, for staleness. Second line: the jar's absolute
        // path, so the datapack loader can skip a pack whose mod has been removed.
        String stamp = jarTime + "\n" + jar.toAbsolutePath() + "\n";
        if (java.nio.file.Files.exists(marker)
                && java.nio.file.Files.readString(marker).equals(stamp)) {
            return 0;
        }

        int extracted = 0;
        try (java.util.zip.ZipFile zip = new java.util.zip.ZipFile(jar.toFile())) {
            var entries = zip.entries();
            while (entries.hasMoreElements()) {
                java.util.zip.ZipEntry entry = entries.nextElement();
                if (entry.isDirectory() || !entry.getName().startsWith("data/")) {
                    continue;
                }
                // Zip entry names are attacker-ish input as far as path handling goes:
                // normalize and refuse anything that escapes the target directory.
                Path out = target.resolve(entry.getName()).normalize();
                if (!out.startsWith(target)) {
                    throw new Exception("refusing to extract " + entry.getName()
                            + " outside " + target);
                }
                java.nio.file.Files.createDirectories(out.getParent());
                try (java.io.InputStream in = zip.getInputStream(entry)) {
                    java.nio.file.Files.copy(in, out,
                            java.nio.file.StandardCopyOption.REPLACE_EXISTING);
                }
                extracted++;
            }
        }

        // The loader wants a pack.mcmeta; the description names the jar so an operator
        // browsing datapacks/ can tell where the pack came from.
        String meta = "{\n  \"pack\": {\n    \"description\": \"data from "
                + jar.getFileName() + "\",\n    \"pack_format\": 81\n  }\n}\n";
        java.nio.file.Files.createDirectories(target);
        java.nio.file.Files.writeString(target.resolve("pack.mcmeta"), meta);
        java.nio.file.Files.writeString(marker, stamp);
        return extracted;
    }
}
