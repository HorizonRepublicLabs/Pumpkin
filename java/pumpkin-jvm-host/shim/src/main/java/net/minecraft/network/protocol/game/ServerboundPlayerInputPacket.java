package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.player.Input;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundPlayerInputPacket(Input input) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundPlayerInputPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerInputPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerInputPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
