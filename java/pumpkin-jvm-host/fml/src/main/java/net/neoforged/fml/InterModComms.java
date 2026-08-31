package net.neoforged.fml;

import dev.pumpkin.shim.Unimplemented;

/**
 * Cross-mod message passing. Nothing fires the enqueue lifecycle here yet, so no mod
 * reaches these members; they exist so the class resolves, and throw by name the day
 * one does.
 */
public final class InterModComms {
    private InterModComms() {
    }

    public static boolean sendTo(String modId, String method, java.util.function.Supplier<?> thing) {
        throw Unimplemented.forMember("net/neoforged/fml/InterModComms.sendTo:(Ljava/lang/String;Ljava/lang/String;Ljava/util/function/Supplier;)Z");
    }

    public static boolean sendTo(String senderModId, String modId, String method, java.util.function.Supplier<?> thing) {
        throw Unimplemented.forMember("net/neoforged/fml/InterModComms.sendTo:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/function/Supplier;)Z");
    }
}
