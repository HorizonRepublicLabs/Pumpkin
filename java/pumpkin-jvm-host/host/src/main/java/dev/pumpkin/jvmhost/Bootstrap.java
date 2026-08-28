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
     * Loads a mod jar, constructs its {@code @Mod} class, and fires {@link RegisterEvent}.
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
