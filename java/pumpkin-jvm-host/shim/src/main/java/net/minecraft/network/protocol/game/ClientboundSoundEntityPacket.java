package net.minecraft.network.protocol.game;

import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSoundEntityPacket implements Packet<ClientGamePacketListener> {

    public ClientboundSoundEntityPacket(Holder<SoundEvent> sound, SoundSource source, Entity sourceEntity, float volume, float pitch, long seed) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundEntityPacket.<init>:(Lnet/minecraft/core/Holder;Lnet/minecraft/sounds/SoundSource;Lnet/minecraft/world/entity/Entity;FFJ)V");
    }

    private ClientboundSoundEntityPacket(RegistryFriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundEntityPacket.<init>:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundEntityPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSoundEntityPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundEntityPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundEntityPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public Holder<SoundEvent> getSound() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundEntityPacket.getSound:()Lnet/minecraft/core/Holder;");
    }

    public SoundSource getSource() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundEntityPacket.getSource:()Lnet/minecraft/sounds/SoundSource;");
    }

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundEntityPacket.getId:()I");
    }

    public float getVolume() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundEntityPacket.getVolume:()F");
    }

    public float getPitch() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundEntityPacket.getPitch:()F");
    }

    public long getSeed() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundEntityPacket.getSeed:()J");
    }

    public ClientboundSoundEntityPacket() {
    }
}
