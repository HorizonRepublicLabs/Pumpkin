package net.neoforged.neoforge.transfer.energy;

import dev.pumpkin.shim.Unimplemented;

public final class EnergyHandlerUtil {

    // Pumpkin divergence: NeoForge's own derivation.
    public static boolean isFull(EnergyHandler handler) {
        return handler.getAmountAsLong() >= handler.getCapacityAsLong();
    }

    protected EnergyHandlerUtil() {
    }
}
