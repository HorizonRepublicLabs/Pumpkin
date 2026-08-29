package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundBlockUpdatePacket implements Packet<ClientGamePacketListener> {

    public ClientboundBlockUpdatePacket(BlockPos pos, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockUpdatePacket.<init>:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public ClientboundBlockUpdatePacket(BlockGetter level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockUpdatePacket.<init>:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)V");
    }

    public PacketType<ClientboundBlockUpdatePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockUpdatePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockUpdatePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockUpdatePacket.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public ClientboundBlockUpdatePacket() {
    }
}
