package net.neoforged.neoforge.transfer;

import net.neoforged.neoforge.transfer.resource.Resource;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import dev.pumpkin.shim.Unimplemented;

public interface ResourceHandler<T extends Resource> {

    int size();

    T getResource(int index);

    long getAmountAsLong(int index);

    long getCapacityAsLong(int index, T resource);

    boolean isValid(int index, T resource);

    int insert(int index, T resource, int amount, TransactionContext transaction);

    default int insert(T resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandler.insert:(Lnet/neoforged/neoforge/transfer/resource/Resource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    int extract(int index, T resource, int amount, TransactionContext transaction);

    default int extract(T resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandler.extract:(Lnet/neoforged/neoforge/transfer/resource/Resource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }
}
