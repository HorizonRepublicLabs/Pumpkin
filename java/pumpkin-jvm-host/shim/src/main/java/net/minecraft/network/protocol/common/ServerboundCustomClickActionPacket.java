package net.minecraft.network.protocol.common;

import java.util.Optional;
import net.minecraft.nbt.Tag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundCustomClickActionPacket(Identifier id, Optional<Tag> payload) implements Packet<ServerCommonPacketListener> {

    public PacketType<ServerboundCustomClickActionPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundCustomClickActionPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerCommonPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/common/ServerboundCustomClickActionPacket.handle:(Lnet/minecraft/network/protocol/common/ServerCommonPacketListener;)V");
    }
}
