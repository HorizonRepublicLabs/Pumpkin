package net.minecraft.network.protocol.configuration;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundFinishConfigurationPacket implements Packet<ServerConfigurationPacketListener> {

    protected ServerboundFinishConfigurationPacket() {
    }

    public PacketType<ServerboundFinishConfigurationPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/configuration/ServerboundFinishConfigurationPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerConfigurationPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/configuration/ServerboundFinishConfigurationPacket.handle:(Lnet/minecraft/network/protocol/configuration/ServerConfigurationPacketListener;)V");
    }

    public boolean isTerminal() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/configuration/ServerboundFinishConfigurationPacket.isTerminal:()Z");
    }
}
