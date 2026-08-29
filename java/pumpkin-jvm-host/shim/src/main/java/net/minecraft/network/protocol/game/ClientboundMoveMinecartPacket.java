package net.minecraft.network.protocol.game;

import java.util.List;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.minecart.NewMinecartBehavior;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundMoveMinecartPacket(int entityId, List<NewMinecartBehavior.MinecartStep> lerpSteps) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundMoveMinecartPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveMinecartPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveMinecartPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public Entity getEntity(Level level) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMoveMinecartPacket.getEntity:(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;");
    }
}
