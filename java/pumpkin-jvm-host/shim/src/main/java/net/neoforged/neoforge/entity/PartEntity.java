package net.neoforged.neoforge.entity;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.server.level.ServerEntity;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public abstract class PartEntity<T extends Entity> extends Entity {

    public PartEntity(T parent) {
    }

    public Packet<ClientGamePacketListener> getAddEntityPacket(ServerEntity entity) {
        throw Unimplemented.forMember("net/neoforged/neoforge/entity/PartEntity.getAddEntityPacket:(Lnet/minecraft/server/level/ServerEntity;)Lnet/minecraft/network/protocol/Packet;");
    }

    public PartEntity() {
    }
}
