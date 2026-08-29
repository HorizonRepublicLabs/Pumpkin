package net.neoforged.neoforge.transfer.energy;

import net.neoforged.neoforge.transfer.transaction.TransactionContext;

public interface EnergyHandler {

    long getAmountAsLong();

    long getCapacityAsLong();

    int insert(int amount, TransactionContext transaction);

    int extract(int amount, TransactionContext transaction);
}
