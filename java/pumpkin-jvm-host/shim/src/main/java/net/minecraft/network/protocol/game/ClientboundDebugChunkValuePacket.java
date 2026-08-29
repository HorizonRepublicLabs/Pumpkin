package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.util.debug.DebugSubscription;
import net.minecraft.world.level.ChunkPos;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundDebugChunkValuePacket(ChunkPos chunkPos, DebugSubscription.Update<?> update) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundDebugChunkValuePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDebugChunkValuePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDebugChunkValuePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
