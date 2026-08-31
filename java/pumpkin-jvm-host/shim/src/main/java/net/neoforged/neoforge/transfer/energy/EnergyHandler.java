package net.neoforged.neoforge.transfer.energy;

import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import dev.pumpkin.shim.Unimplemented;

public interface EnergyHandler {

    long getAmountAsLong();

    // Pumpkin divergence: NeoForge's own derivations -- the long amount, clamped.
    default int getAmountAsInt() {
        return (int) Math.min(Integer.MAX_VALUE, getAmountAsLong());
    }

    long getCapacityAsLong();

    default int getCapacityAsInt() {
        return (int) Math.min(Integer.MAX_VALUE, getCapacityAsLong());
    }

    int insert(int amount, TransactionContext transaction);

    int extract(int amount, TransactionContext transaction);
}
