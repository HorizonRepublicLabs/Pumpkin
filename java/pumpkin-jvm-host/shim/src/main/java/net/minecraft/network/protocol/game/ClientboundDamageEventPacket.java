package net.minecraft.network.protocol.game;

import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundDamageEventPacket(int entityId, Holder<DamageType> sourceType, int sourceCauseId, int sourceDirectId, Optional<Vec3> sourcePosition) implements Packet<ClientGamePacketListener> {

    public ClientboundDamageEventPacket(Entity entity, DamageSource source) {
        this((int) 0, (Holder<DamageType>) null, (int) 0, (int) 0, (Optional<Vec3>) null);
    }

    private ClientboundDamageEventPacket(RegistryFriendlyByteBuf input) {
        this((int) 0, (Holder<DamageType>) null, (int) 0, (int) 0, (Optional<Vec3>) null);
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDamageEventPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundDamageEventPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDamageEventPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundDamageEventPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
