package net.minecraft.network.protocol.game;

import net.minecraft.core.Holder;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSoundPacket implements Packet<ClientGamePacketListener> {

    public ClientboundSoundPacket(Holder<SoundEvent> sound, SoundSource source, double x, double y, double z, float volume, float pitch, long seed) {
    }

    private ClientboundSoundPacket(RegistryFriendlyByteBuf input) {
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSoundPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public Holder<SoundEvent> getSound() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundPacket.getSound:()Lnet/minecraft/core/Holder;");
    }

    public SoundSource getSource() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundPacket.getSource:()Lnet/minecraft/sounds/SoundSource;");
    }

    public double getX() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundPacket.getX:()D");
    }

    public double getY() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundPacket.getY:()D");
    }

    public double getZ() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundPacket.getZ:()D");
    }

    public float getVolume() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundPacket.getVolume:()F");
    }

    public float getPitch() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundPacket.getPitch:()F");
    }

    public long getSeed() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSoundPacket.getSeed:()J");
    }

    public ClientboundSoundPacket() {
    }
}
