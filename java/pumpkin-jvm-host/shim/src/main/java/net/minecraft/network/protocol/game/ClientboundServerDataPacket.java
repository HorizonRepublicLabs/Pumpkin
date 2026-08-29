package net.minecraft.network.protocol.game;

import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundServerDataPacket(Component motd, Optional<byte[]> iconBytes) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundServerDataPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundServerDataPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundServerDataPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
