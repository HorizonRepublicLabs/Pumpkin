package net.minecraft.network.protocol.configuration;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundAcceptCodeOfConductPacket() implements Packet<ServerConfigurationPacketListener> {

    public PacketType<ServerboundAcceptCodeOfConductPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/configuration/ServerboundAcceptCodeOfConductPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerConfigurationPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/configuration/ServerboundAcceptCodeOfConductPacket.handle:(Lnet/minecraft/network/protocol/configuration/ServerConfigurationPacketListener;)V");
    }
}
