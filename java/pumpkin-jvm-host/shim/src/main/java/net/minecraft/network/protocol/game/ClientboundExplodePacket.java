package net.minecraft.network.protocol.game;

import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ExplosionParticleInfo;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundExplodePacket(Vec3 center, float radius, int blockCount, Optional<Vec3> playerKnockback, ParticleOptions explosionParticle, Holder<SoundEvent> explosionSound, WeightedList<ExplosionParticleInfo> blockParticles) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundExplodePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundExplodePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundExplodePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
