package net.minecraft.network.protocol.game;

import java.util.List;
import java.util.Optional;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundEditBookPacket(int slot, List<String> pages, Optional<String> title) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundEditBookPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundEditBookPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundEditBookPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
