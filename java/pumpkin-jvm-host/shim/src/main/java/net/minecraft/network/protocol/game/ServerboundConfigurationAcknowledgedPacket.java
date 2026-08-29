package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundConfigurationAcknowledgedPacket implements Packet<ServerGamePacketListener> {

    protected ServerboundConfigurationAcknowledgedPacket() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundConfigurationAcknowledgedPacket.<init>:()V");
    }

    public PacketType<ServerboundConfigurationAcknowledgedPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundConfigurationAcknowledgedPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundConfigurationAcknowledgedPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public boolean isTerminal() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundConfigurationAcknowledgedPacket.isTerminal:()Z");
    }
}
