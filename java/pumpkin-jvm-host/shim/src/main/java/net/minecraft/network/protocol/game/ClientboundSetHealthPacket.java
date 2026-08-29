package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetHealthPacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetHealthPacket(float health, int food, float saturation) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetHealthPacket.<init>:(FIF)V");
    }

    private ClientboundSetHealthPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetHealthPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetHealthPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetHealthPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetHealthPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetHealthPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public ClientboundSetHealthPacket() {
    }
}
