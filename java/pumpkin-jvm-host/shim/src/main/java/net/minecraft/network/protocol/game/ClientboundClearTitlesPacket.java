package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundClearTitlesPacket implements Packet<ClientGamePacketListener> {

    public ClientboundClearTitlesPacket(boolean resetTimes) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundClearTitlesPacket.<init>:(Z)V");
    }

    private ClientboundClearTitlesPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundClearTitlesPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundClearTitlesPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundClearTitlesPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundClearTitlesPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundClearTitlesPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    protected ClientboundClearTitlesPacket() {
    }
}
