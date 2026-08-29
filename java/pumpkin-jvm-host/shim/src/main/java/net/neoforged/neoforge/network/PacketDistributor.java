package net.neoforged.neoforge.network;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;
import dev.pumpkin.shim.Unimplemented;

public final class PacketDistributor {

    protected PacketDistributor() {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/PacketDistributor.<init>:()V");
    }

    public static void sendToPlayer(ServerPlayer player, CustomPacketPayload payload, CustomPacketPayload... payloads) {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/PacketDistributor.sendToPlayer:(Lnet/minecraft/server/level/ServerPlayer;Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;[Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;)V");
    }

    public static void sendToAllPlayers(CustomPacketPayload payload, CustomPacketPayload... payloads) {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/PacketDistributor.sendToAllPlayers:(Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;[Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;)V");
    }
}
