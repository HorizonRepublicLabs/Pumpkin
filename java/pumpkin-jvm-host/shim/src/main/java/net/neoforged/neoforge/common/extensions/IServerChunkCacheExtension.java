package net.neoforged.neoforge.common.extensions;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public interface IServerChunkCacheExtension {

    default void sendToTrackingPlayers(Entity entity, CustomPacketPayload payload) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IServerChunkCacheExtension.sendToTrackingPlayers:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;)V");
    }
}
