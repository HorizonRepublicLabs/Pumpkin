package net.neoforged.neoforge.transfer;

import net.neoforged.neoforge.transfer.resource.Resource;
import dev.pumpkin.shim.Unimplemented;

public class TransferPreconditions {

    protected TransferPreconditions() {
    }

    public static void checkNonEmpty(Resource resource) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/TransferPreconditions.checkNonEmpty:(Lnet/neoforged/neoforge/transfer/resource/Resource;)V");
    }

    public static void checkNonNegative(int value) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/TransferPreconditions.checkNonNegative:(I)V");
    }

    public static void checkNonEmptyNonNegative(Resource resource, int value) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/TransferPreconditions.checkNonEmptyNonNegative:(Lnet/neoforged/neoforge/transfer/resource/Resource;I)V");
    }
}
