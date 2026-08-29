package net.minecraft.network.protocol.game;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.effect.MobEffectInstance;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundUpdateMobEffectPacket implements Packet<ClientGamePacketListener> {

    public ClientboundUpdateMobEffectPacket(int entityId, MobEffectInstance effect, boolean blend) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateMobEffectPacket.<init>:(ILnet/minecraft/world/effect/MobEffectInstance;Z)V");
    }

    private ClientboundUpdateMobEffectPacket(RegistryFriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateMobEffectPacket.<init>:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateMobEffectPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundUpdateMobEffectPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateMobEffectPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateMobEffectPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    protected ClientboundUpdateMobEffectPacket() {
    }
}
