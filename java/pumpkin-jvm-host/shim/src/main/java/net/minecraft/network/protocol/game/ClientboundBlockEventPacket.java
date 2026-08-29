package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.block.Block;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundBlockEventPacket implements Packet<ClientGamePacketListener> {

    public ClientboundBlockEventPacket(BlockPos pos, Block block, int b0, int b1) {
    }

    private ClientboundBlockEventPacket(RegistryFriendlyByteBuf input) {
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockEventPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundBlockEventPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockEventPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockEventPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockEventPacket.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public ClientboundBlockEventPacket() {
    }
}
