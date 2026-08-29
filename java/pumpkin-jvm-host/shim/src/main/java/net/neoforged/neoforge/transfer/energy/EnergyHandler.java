package net.neoforged.neoforge.transfer.energy;

import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import dev.pumpkin.shim.Unimplemented;

public interface EnergyHandler {

    long getAmountAsLong();

    default int getAmountAsInt() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/EnergyHandler.getAmountAsInt:()I");
    }

    long getCapacityAsLong();

    default int getCapacityAsInt() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/energy/EnergyHandler.getCapacityAsInt:()I");
    }

    int insert(int amount, TransactionContext transaction);

    int extract(int amount, TransactionContext transaction);
}
