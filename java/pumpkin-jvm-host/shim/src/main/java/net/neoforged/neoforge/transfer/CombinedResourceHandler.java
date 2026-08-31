package net.neoforged.neoforge.transfer;

import java.util.SequencedCollection;
import net.neoforged.neoforge.transfer.resource.Resource;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import dev.pumpkin.shim.Unimplemented;

public class CombinedResourceHandler<T extends Resource> implements ResourceHandler<T> {

    public CombinedResourceHandler(SequencedCollection<? extends ResourceHandler<T>> handlers) {
    }

    public CombinedResourceHandler(ResourceHandler<T>... handlers) {
    }

    public final int size() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/CombinedResourceHandler.size:()I");
    }

    public T getResource(int index) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/CombinedResourceHandler.getResource:(I)Lnet/neoforged/neoforge/transfer/resource/Resource;");
    }

    public long getAmountAsLong(int index) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/CombinedResourceHandler.getAmountAsLong:(I)J");
    }

    public long getCapacityAsLong(int index, T resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/CombinedResourceHandler.getCapacityAsLong:(ILnet/neoforged/neoforge/transfer/resource/Resource;)J");
    }

    public boolean isValid(int index, T resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/CombinedResourceHandler.isValid:(ILnet/neoforged/neoforge/transfer/resource/Resource;)Z");
    }

    public int insert(int index, T resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/CombinedResourceHandler.insert:(ILnet/neoforged/neoforge/transfer/resource/Resource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    public int insert(T resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/CombinedResourceHandler.insert:(Lnet/neoforged/neoforge/transfer/resource/Resource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    public int extract(int index, T resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/CombinedResourceHandler.extract:(ILnet/neoforged/neoforge/transfer/resource/Resource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    public int extract(T resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/CombinedResourceHandler.extract:(Lnet/neoforged/neoforge/transfer/resource/Resource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    public CombinedResourceHandler() {
    }
}
