package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundSetHeldSlotPacket(int slot) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundSetHeldSlotPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetHeldSlotPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetHeldSlotPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
