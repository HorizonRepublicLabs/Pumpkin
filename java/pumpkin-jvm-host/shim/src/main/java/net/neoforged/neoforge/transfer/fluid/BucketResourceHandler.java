package net.neoforged.neoforge.transfer.fluid;

import net.neoforged.neoforge.transfer.ItemAccessResourceHandler;
import net.neoforged.neoforge.transfer.access.ItemAccess;
import net.neoforged.neoforge.transfer.item.ItemResource;
import dev.pumpkin.shim.Unimplemented;

public final class BucketResourceHandler extends ItemAccessResourceHandler<FluidResource> {

    public BucketResourceHandler(ItemAccess itemAccess) {
    }

    protected FluidResource getResourceFrom(ItemResource accessResource, int index) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/BucketResourceHandler.getResourceFrom:(Lnet/neoforged/neoforge/transfer/item/ItemResource;I)Lnet/neoforged/neoforge/transfer/fluid/FluidResource;");
    }

    protected int getAmountFrom(ItemResource accessResource, int index) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/BucketResourceHandler.getAmountFrom:(Lnet/neoforged/neoforge/transfer/item/ItemResource;I)I");
    }

    protected ItemResource update(ItemResource accessResource, int index, FluidResource newResource, int newAmount) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/BucketResourceHandler.update:(Lnet/neoforged/neoforge/transfer/item/ItemResource;ILnet/neoforged/neoforge/transfer/fluid/FluidResource;I)Lnet/neoforged/neoforge/transfer/item/ItemResource;");
    }

    protected int getCapacity(int index, FluidResource resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/BucketResourceHandler.getCapacity:(ILnet/neoforged/neoforge/transfer/fluid/FluidResource;)I");
    }

    public BucketResourceHandler() {
    }
}
