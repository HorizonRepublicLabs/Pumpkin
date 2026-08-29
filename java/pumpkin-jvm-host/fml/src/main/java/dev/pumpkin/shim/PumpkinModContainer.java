package dev.pumpkin.shim;

import net.neoforged.fml.ModContainer;

/**
 * The {@link ModContainer} handed to a mod's constructor.
 *
 * <p>NeoForge injects a mod's constructor arguments by type, and both real mods measured so
 * far declare {@code (IEventBus, ModContainer)}. {@code ModContainer} is abstract, so
 * something concrete has to exist for that injection to work at all.
 *
 * <p>It adds nothing to the abstract class. Every method {@code ModContainer} declares still
 * throws {@link Unimplemented}, which is the point: a mod that merely receives the container
 * runs, and a mod that calls {@code registerConfig} on it stops with a key naming exactly
 * what is missing. Carrying the mod id makes that failure name the mod rather than the type.
 */
public final class PumpkinModContainer extends ModContainer {
    private final String modId;

    public PumpkinModContainer(String modId) {
        this.modId = modId;
    }

    /** The mod this container was created for. */
    public String modId() {
        return modId;
    }

    @Override
    public String toString() {
        return "PumpkinModContainer[" + modId + "]";
    }
}
