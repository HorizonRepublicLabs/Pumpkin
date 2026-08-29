package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundAnimatePacket implements Packet<ClientGamePacketListener> {

    public ClientboundAnimatePacket(Entity entity, int action) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAnimatePacket.<init>:(Lnet/minecraft/world/entity/Entity;I)V");
    }

    private ClientboundAnimatePacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAnimatePacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAnimatePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundAnimatePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAnimatePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAnimatePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAnimatePacket.getId:()I");
    }

    public ClientboundAnimatePacket() {
    }
}
