package net.minecraft.network.protocol.common;

import java.util.Optional;
import java.util.UUID;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundResourcePackPushPacket(UUID id, String url, String hash, boolean required, Optional<Component> prompt) implements Packet<ClientCommonPacketListener> {

    public PacketType<ClientboundResourcePackPushPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundResourcePackPushPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ClientboundResourcePackPushPacket.handle:(Lnet/minecraft/network/protocol/common/ClientCommonPacketListener;)V");
    }
}
