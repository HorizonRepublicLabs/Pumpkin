package net.neoforged.neoforge.transfer;

import dev.pumpkin.shim.Unimplemented;

public class TransferPreconditions {

    protected TransferPreconditions() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/TransferPreconditions.<init>:()V");
    }

    public static void checkNonNegative(int value) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/TransferPreconditions.checkNonNegative:(I)V");
    }
}
