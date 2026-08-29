package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.util.debug.DebugSubscription;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundDebugEntityValuePacket(int entityId, DebugSubscription.Update<?> update) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundDebugEntityValuePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDebugEntityValuePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDebugEntityValuePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
