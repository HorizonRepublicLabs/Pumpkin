package net.minecraft.network.protocol.game;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.effect.MobEffectInstance;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundUpdateMobEffectPacket implements Packet<ClientGamePacketListener> {

    public ClientboundUpdateMobEffectPacket(int entityId, MobEffectInstance effect, boolean blend) {
    }

    private ClientboundUpdateMobEffectPacket(RegistryFriendlyByteBuf input) {
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

    public ClientboundUpdateMobEffectPacket() {
    }
}
