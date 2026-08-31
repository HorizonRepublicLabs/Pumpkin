package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundMoveVehiclePacket(Vec3 position, float yRot, float xRot) implements Packet<ClientGamePacketListener> {

    public static ClientboundMoveVehiclePacket fromEntity(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveVehiclePacket.fromEntity:(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/network/protocol/game/ClientboundMoveVehiclePacket;");
    }

    public PacketType<ClientboundMoveVehiclePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveVehiclePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveVehiclePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
