package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundChunkBatchReceivedPacket(float desiredChunksPerTick) implements Packet<ServerGamePacketListener> {

    private ServerboundChunkBatchReceivedPacket(FriendlyByteBuf input) {
        this((float) 0.0F);
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChunkBatchReceivedPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundChunkBatchReceivedPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChunkBatchReceivedPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundChunkBatchReceivedPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
