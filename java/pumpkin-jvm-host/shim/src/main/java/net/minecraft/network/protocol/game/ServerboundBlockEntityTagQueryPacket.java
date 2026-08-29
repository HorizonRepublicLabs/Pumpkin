package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundBlockEntityTagQueryPacket implements Packet<ServerGamePacketListener> {

    public ServerboundBlockEntityTagQueryPacket(int transactionId, BlockPos pos) {
    }

    private ServerboundBlockEntityTagQueryPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundBlockEntityTagQueryPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundBlockEntityTagQueryPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundBlockEntityTagQueryPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundBlockEntityTagQueryPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundBlockEntityTagQueryPacket.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public ServerboundBlockEntityTagQueryPacket() {
    }
}
