package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundCooldownPacket(Identifier cooldownGroup, int duration) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundCooldownPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCooldownPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundCooldownPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
