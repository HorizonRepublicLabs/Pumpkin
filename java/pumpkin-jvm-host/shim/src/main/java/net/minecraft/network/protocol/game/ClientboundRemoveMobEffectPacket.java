package net.minecraft.network.protocol.game;

import net.minecraft.core.Holder;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundRemoveMobEffectPacket(int entityId, Holder<MobEffect> effect) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundRemoveMobEffectPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundRemoveMobEffectPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundRemoveMobEffectPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public Entity getEntity(Level level) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundRemoveMobEffectPacket.getEntity:(Lnet/minecraft/world/level/Level;)Lnet/minecraft/world/entity/Entity;");
    }
}
