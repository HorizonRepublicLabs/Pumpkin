package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.InteractionHand;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundOpenBookPacket implements Packet<ClientGamePacketListener> {

    public ClientboundOpenBookPacket(InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundOpenBookPacket.<init>:(Lnet/minecraft/world/InteractionHand;)V");
    }

    private ClientboundOpenBookPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundOpenBookPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundOpenBookPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundOpenBookPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundOpenBookPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundOpenBookPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public ClientboundOpenBookPacket() {
    }
}
