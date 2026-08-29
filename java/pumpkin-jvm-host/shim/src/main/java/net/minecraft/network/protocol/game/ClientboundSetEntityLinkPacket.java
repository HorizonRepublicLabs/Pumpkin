package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetEntityLinkPacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetEntityLinkPacket(Entity sourceEntity, Entity destEntity) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEntityLinkPacket.<init>:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;)V");
    }

    private ClientboundSetEntityLinkPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEntityLinkPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEntityLinkPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetEntityLinkPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEntityLinkPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEntityLinkPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    protected ClientboundSetEntityLinkPacket() {
    }
}
