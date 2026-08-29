package net.minecraft.network.protocol.game;

import java.util.BitSet;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.lighting.LevelLightEngine;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundLevelChunkWithLightPacket implements Packet<ClientGamePacketListener> {

    public ClientboundLevelChunkWithLightPacket(LevelChunk levelChunk, LevelLightEngine lightEngine, BitSet skyChangedLightSectionFilter, BitSet blockChangedLightSectionFilter) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelChunkWithLightPacket.<init>:(Lnet/minecraft/world/level/chunk/LevelChunk;Lnet/minecraft/world/level/lighting/LevelLightEngine;Ljava/util/BitSet;Ljava/util/BitSet;)V");
    }

    private ClientboundLevelChunkWithLightPacket(RegistryFriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelChunkWithLightPacket.<init>:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelChunkWithLightPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundLevelChunkWithLightPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelChunkWithLightPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelChunkWithLightPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public int getX() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelChunkWithLightPacket.getX:()I");
    }

    public int getZ() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelChunkWithLightPacket.getZ:()I");
    }

    public ClientboundLevelChunkWithLightPacket() {
    }
}
