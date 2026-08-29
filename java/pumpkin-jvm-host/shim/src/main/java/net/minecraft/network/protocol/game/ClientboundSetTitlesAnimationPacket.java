package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetTitlesAnimationPacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetTitlesAnimationPacket(int fadeIn, int stay, int fadeOut) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetTitlesAnimationPacket.<init>:(III)V");
    }

    private ClientboundSetTitlesAnimationPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetTitlesAnimationPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetTitlesAnimationPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetTitlesAnimationPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetTitlesAnimationPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetTitlesAnimationPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    protected ClientboundSetTitlesAnimationPacket() {
    }
}
