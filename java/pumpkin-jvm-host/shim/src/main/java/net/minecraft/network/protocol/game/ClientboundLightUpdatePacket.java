package net.minecraft.network.protocol.game;

import java.util.BitSet;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.lighting.LevelLightEngine;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundLightUpdatePacket implements Packet<ClientGamePacketListener> {

    public ClientboundLightUpdatePacket(ChunkPos pos, LevelLightEngine lightEngine, BitSet skyChangedLightSectionFilter, BitSet blockChangedLightSectionFilter) {
    }

    private ClientboundLightUpdatePacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLightUpdatePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundLightUpdatePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLightUpdatePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLightUpdatePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public int getX() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLightUpdatePacket.getX:()I");
    }

    public int getZ() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLightUpdatePacket.getZ:()I");
    }

    public ClientboundLightUpdatePacket() {
    }
}
