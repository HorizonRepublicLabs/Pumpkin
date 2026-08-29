package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundSetEntityMotionPacket(int id, Vec3 movement) implements Packet<ClientGamePacketListener> {

    public ClientboundSetEntityMotionPacket(Entity entity) {
        this((int) 0, (Vec3) null);
    }

    public PacketType<ClientboundSetEntityMotionPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEntityMotionPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEntityMotionPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
