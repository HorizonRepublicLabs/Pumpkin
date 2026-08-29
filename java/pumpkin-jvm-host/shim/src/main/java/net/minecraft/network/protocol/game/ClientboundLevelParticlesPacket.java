package net.minecraft.network.protocol.game;

import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundLevelParticlesPacket implements Packet<ClientGamePacketListener> {

    public <T extends ParticleOptions> ClientboundLevelParticlesPacket(T particle, boolean overrideLimiter, boolean alwaysShow, double x, double y, double z, float xDist, float yDist, float zDist, float maxSpeed, int count) {
    }

    private ClientboundLevelParticlesPacket(RegistryFriendlyByteBuf input) {
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelParticlesPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundLevelParticlesPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelParticlesPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelParticlesPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public double getX() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelParticlesPacket.getX:()D");
    }

    public double getY() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelParticlesPacket.getY:()D");
    }

    public double getZ() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelParticlesPacket.getZ:()D");
    }

    public int getCount() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelParticlesPacket.getCount:()I");
    }

    public ClientboundLevelParticlesPacket() {
    }
}
