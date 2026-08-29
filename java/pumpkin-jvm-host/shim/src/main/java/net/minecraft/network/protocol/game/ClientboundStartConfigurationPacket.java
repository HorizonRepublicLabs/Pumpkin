package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundStartConfigurationPacket implements Packet<ClientGamePacketListener> {

    protected ClientboundStartConfigurationPacket() {
    }

    public PacketType<ClientboundStartConfigurationPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundStartConfigurationPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundStartConfigurationPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public boolean isTerminal() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundStartConfigurationPacket.isTerminal:()Z");
    }
}
