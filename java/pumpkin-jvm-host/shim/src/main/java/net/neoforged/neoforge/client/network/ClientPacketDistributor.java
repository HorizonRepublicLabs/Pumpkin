package net.neoforged.neoforge.client.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import dev.pumpkin.shim.Unimplemented;

public final class ClientPacketDistributor {

    protected ClientPacketDistributor() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/network/ClientPacketDistributor.<init>:()V");
    }

    public static void sendToServer(CustomPacketPayload payload, CustomPacketPayload... payloads) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/network/ClientPacketDistributor.sendToServer:(Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;[Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;)V");
    }
}
