package dev.pumpkin.jvmhost;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import net.neoforged.bus.api.IEventBus;
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
        DeferredRegister.setSink(PumpkinHost::registerBlock);
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
        IEventBus bus = new IEventBus();

        Constructor<?> constructor = candidate.mainClass().getConstructor(IEventBus.class);
        try {
            constructor.newInstance(bus);
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
        return candidate.modId();
    }
}
