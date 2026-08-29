package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundChunkBatchStartPacket implements Packet<ClientGamePacketListener> {

    protected ClientboundChunkBatchStartPacket() {
    }

    public PacketType<ClientboundChunkBatchStartPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundChunkBatchStartPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundChunkBatchStartPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
