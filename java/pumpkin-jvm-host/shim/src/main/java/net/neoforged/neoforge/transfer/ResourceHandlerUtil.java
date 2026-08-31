package net.neoforged.neoforge.transfer;

import java.util.function.Predicate;
import net.neoforged.neoforge.transfer.resource.Resource;
import net.neoforged.neoforge.transfer.resource.ResourceStack;
import net.neoforged.neoforge.transfer.transaction.TransactionContext;
import dev.pumpkin.shim.Unimplemented;

public final class ResourceHandlerUtil {

    protected ResourceHandlerUtil() {
    }

    public static boolean isEmpty(Resource resource, int amount) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandlerUtil.isEmpty:(Lnet/neoforged/neoforge/transfer/resource/Resource;I)Z");
    }

    public static boolean isEmpty(ResourceHandler<? extends Resource> handler) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandlerUtil.isEmpty:(Lnet/neoforged/neoforge/transfer/ResourceHandler;)Z");
    }

    public static <T extends Resource> boolean isFull(ResourceHandler<T> handler) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandlerUtil.isFull:(Lnet/neoforged/neoforge/transfer/ResourceHandler;)Z");
    }

    public static <T extends Resource> boolean isValid(ResourceHandler<T> handler, T resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandlerUtil.isValid:(Lnet/neoforged/neoforge/transfer/ResourceHandler;Lnet/neoforged/neoforge/transfer/resource/Resource;)Z");
    }

    public static <T extends Resource> int getRedstoneSignalFromResourceHandler(ResourceHandler<T> handler) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandlerUtil.getRedstoneSignalFromResourceHandler:(Lnet/neoforged/neoforge/transfer/ResourceHandler;)I");
    }

    public static <T extends Resource> int insertStacking(ResourceHandler<T> handler, T resource, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandlerUtil.insertStacking:(Lnet/neoforged/neoforge/transfer/ResourceHandler;Lnet/neoforged/neoforge/transfer/resource/Resource;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)I");
    }

    public static <T extends Resource> ResourceStack<T> extractFirst(ResourceHandler<T> handler, Predicate<T> filter, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandlerUtil.extractFirst:(Lnet/neoforged/neoforge/transfer/ResourceHandler;Ljava/util/function/Predicate;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)Lnet/neoforged/neoforge/transfer/resource/ResourceStack;");
    }

    public static <T extends Resource> ResourceStack<T> moveFirst(ResourceHandler<T> from, ResourceHandler<T> to, Predicate<T> filter, int amount, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandlerUtil.moveFirst:(Lnet/neoforged/neoforge/transfer/ResourceHandler;Lnet/neoforged/neoforge/transfer/ResourceHandler;Ljava/util/function/Predicate;ILnet/neoforged/neoforge/transfer/transaction/TransactionContext;)Lnet/neoforged/neoforge/transfer/resource/ResourceStack;");
    }

    public static <T extends Resource> boolean contains(ResourceHandler<T> handler, T resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandlerUtil.contains:(Lnet/neoforged/neoforge/transfer/ResourceHandler;Lnet/neoforged/neoforge/transfer/resource/Resource;)Z");
    }

    public static <T extends Resource> T findExtractableResource(ResourceHandler<T> handler, Predicate<T> filter, TransactionContext transaction) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandlerUtil.findExtractableResource:(Lnet/neoforged/neoforge/transfer/ResourceHandler;Ljava/util/function/Predicate;Lnet/neoforged/neoforge/transfer/transaction/TransactionContext;)Lnet/neoforged/neoforge/transfer/resource/Resource;");
    }
}
