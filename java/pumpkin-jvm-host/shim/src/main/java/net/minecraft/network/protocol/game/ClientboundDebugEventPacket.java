package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.util.debug.DebugSubscription;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundDebugEventPacket(DebugSubscription.Event<?> event) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundDebugEventPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDebugEventPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDebugEventPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
