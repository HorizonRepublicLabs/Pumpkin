package net.neoforged.neoforge.transfer;

import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.resource.Resource;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import dev.pumpkin.shim.Unimplemented;

public abstract class ItemAccessResourceHandler<T extends Resource> implements ResourceHandler<T> {

    protected ItemAccessResourceHandler(ItemAccess itemAccess, int size) {
    }

    protected abstract T getResourceFrom(ItemResource accessResource, int index);

    protected abstract int getAmountFrom(ItemResource accessResource, int index);

    protected abstract ItemResource update(ItemResource accessResource, int index, T newResource, int newAmount);

    public boolean isValid(int index, T resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ItemAccessResourceHandler.isValid:(ILnet/neoforged/neoforge/transfer/resource/Resource;)Z");
    }

    protected abstract int getCapacity(int index, T resource);

    public int size() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ItemAccessResourceHandler.size:()I");
    }

    public T getResource(int index) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ItemAccessResourceHandler.getResource:(I)Lnet/neoforged/neoforge/transfer/resource/Resource;");
    }

    public long getAmountAsLong(int index) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ItemAccessResourceHandler.getAmountAsLong:(I)J");
    }

    public long getCapacityAsLong(int index, T resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ItemAccessResourceHandler.getCapacityAsLong:(ILnet/neoforged/neoforge/transfer/resource/Resource;)J");
    }

    public int insert(int index, T resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ItemAccessResourceHandler.insert:(ILnet/neoforged/neoforge/transfer/resource/Resource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    public int extract(int index, T resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ItemAccessResourceHandler.extract:(ILnet/neoforged/neoforge/transfer/resource/Resource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    public ItemAccessResourceHandler() {
    }
}
