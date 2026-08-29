package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.BundlePacket;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundBundlePacket extends BundlePacket<ClientGamePacketListener> {

    public ClientboundBundlePacket(Iterable<Packet<? super ClientGamePacketListener>> packets) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBundlePacket.<init>:(Ljava/lang/Iterable;)V");
    }

    public PacketType<ClientboundBundlePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBundlePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBundlePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    protected ClientboundBundlePacket() {
    }
}
