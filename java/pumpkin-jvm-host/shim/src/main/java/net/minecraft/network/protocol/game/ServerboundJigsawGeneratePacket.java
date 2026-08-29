package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundJigsawGeneratePacket implements Packet<ServerGamePacketListener> {

    public ServerboundJigsawGeneratePacket(BlockPos blockPos, int levels, boolean keepJigsaws) {
    }

    private ServerboundJigsawGeneratePacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundJigsawGeneratePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundJigsawGeneratePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundJigsawGeneratePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundJigsawGeneratePacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundJigsawGeneratePacket.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public int levels() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundJigsawGeneratePacket.levels:()I");
    }

    public ServerboundJigsawGeneratePacket() {
    }
}
