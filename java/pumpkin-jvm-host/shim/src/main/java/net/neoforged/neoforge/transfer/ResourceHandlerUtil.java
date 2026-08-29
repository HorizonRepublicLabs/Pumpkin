package net.neoforged.neoforge.transfer;

import net.neoforged.neoforge.transfer.resource.Resource;
import dev.pumpkin.shim.Unimplemented;

public final class ResourceHandlerUtil {

    protected ResourceHandlerUtil() {
    }

    public static <T extends Resource> boolean isValid(ResourceHandler<T> handler, T resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandlerUtil.isValid:(Lnet/neoforged/neoforge/transfer/ResourceHandler;Lnet/neoforged/neoforge/transfer/resource/Resource;)Z");
    }

    public static <T extends Resource> int getRedstoneSignalFromResourceHandler(ResourceHandler<T> handler) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandlerUtil.getRedstoneSignalFromResourceHandler:(Lnet/neoforged/neoforge/transfer/ResourceHandler;)I");
    }
}
