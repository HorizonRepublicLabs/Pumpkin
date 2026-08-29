package net.minecraft.network.protocol.common;

import java.util.Map;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagNetworkSerialization;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundUpdateTagsPacket implements Packet<ClientCommonPacketListener> {

    public ClientboundUpdateTagsPacket(Map<ResourceKey<? extends Registry<?>>, TagNetworkSerialization.NetworkPayload> tags) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundUpdateTagsPacket.<init>:(Ljava/util/Map;)V");
    }

    private ClientboundUpdateTagsPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundUpdateTagsPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundUpdateTagsPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundUpdateTagsPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundUpdateTagsPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundUpdateTagsPacket.handle:(Lnet/minecraft/network/protocol/common/ClientCommonPacketListener;)V");
    }

    public Map<ResourceKey<? extends Registry<?>>, TagNetworkSerialization.NetworkPayload> getTags() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundUpdateTagsPacket.getTags:()Ljava/util/Map;");
    }

    public ClientboundUpdateTagsPacket() {
    }
}
