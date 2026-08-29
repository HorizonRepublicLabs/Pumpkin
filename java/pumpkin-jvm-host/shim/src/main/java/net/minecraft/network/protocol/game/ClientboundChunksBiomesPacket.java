package net.minecraft.network.protocol.game;

import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.LevelChunk;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundChunksBiomesPacket(List<ClientboundChunksBiomesPacket.ChunkBiomeData> chunkBiomeData) implements Packet<ClientGamePacketListener> {

    private ClientboundChunksBiomesPacket(FriendlyByteBuf input) {
        this((List<ClientboundChunksBiomesPacket.ChunkBiomeData>) null);
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundChunksBiomesPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundChunksBiomesPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundChunksBiomesPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundChunksBiomesPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public record ChunkBiomeData(ChunkPos pos, byte[] buffer) {

        public ChunkBiomeData(LevelChunk chunk) {
            this((ChunkPos) null, (byte[]) null);
        }

        public ChunkBiomeData(FriendlyByteBuf input) {
            this((ChunkPos) null, (byte[]) null);
        }

        public void write(FriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundChunksBiomesPacket$ChunkBiomeData.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
        }
    }
}
