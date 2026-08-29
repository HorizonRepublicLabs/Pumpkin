package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetPassengersPacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetPassengersPacket(Entity vehicle) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPassengersPacket.<init>:(Lnet/minecraft/world/entity/Entity;)V");
    }

    private ClientboundSetPassengersPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPassengersPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPassengersPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetPassengersPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPassengersPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPassengersPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public ClientboundSetPassengersPacket() {
    }
}
