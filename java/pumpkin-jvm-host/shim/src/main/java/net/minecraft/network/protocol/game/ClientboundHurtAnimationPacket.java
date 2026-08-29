package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.LivingEntity;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundHurtAnimationPacket(int id, float yaw) implements Packet<ClientGamePacketListener> {

    public ClientboundHurtAnimationPacket(LivingEntity entity) {
        this((int) 0, (float) 0.0F);
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundHurtAnimationPacket.<init>:(Lnet/minecraft/world/entity/LivingEntity;)V");
    }

    private ClientboundHurtAnimationPacket(FriendlyByteBuf input) {
        this((int) 0, (float) 0.0F);
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundHurtAnimationPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundHurtAnimationPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundHurtAnimationPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundHurtAnimationPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundHurtAnimationPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
