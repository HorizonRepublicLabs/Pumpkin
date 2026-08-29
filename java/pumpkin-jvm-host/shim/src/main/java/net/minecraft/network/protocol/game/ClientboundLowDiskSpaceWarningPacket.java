package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundLowDiskSpaceWarningPacket implements Packet<ClientGamePacketListener> {

    protected ClientboundLowDiskSpaceWarningPacket() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLowDiskSpaceWarningPacket.<init>:()V");
    }

    public PacketType<ClientboundLowDiskSpaceWarningPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLowDiskSpaceWarningPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLowDiskSpaceWarningPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
