package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.block.entity.CommandBlockEntity;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundSetCommandBlockPacket implements Packet<ServerGamePacketListener> {

    public ServerboundSetCommandBlockPacket(BlockPos pos, String command, CommandBlockEntity.Mode mode, boolean trackOutput, boolean conditional, boolean automatic) {
    }

    private ServerboundSetCommandBlockPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetCommandBlockPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundSetCommandBlockPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetCommandBlockPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetCommandBlockPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetCommandBlockPacket.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public ServerboundSetCommandBlockPacket() {
    }
}
