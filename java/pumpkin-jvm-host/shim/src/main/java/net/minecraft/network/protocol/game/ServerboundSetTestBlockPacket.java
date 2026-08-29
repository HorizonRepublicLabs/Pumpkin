package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.block.state.properties.TestBlockMode;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundSetTestBlockPacket(BlockPos position, TestBlockMode mode, String message) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundSetTestBlockPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetTestBlockPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetTestBlockPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
