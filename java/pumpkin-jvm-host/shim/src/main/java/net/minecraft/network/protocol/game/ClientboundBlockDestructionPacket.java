package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundBlockDestructionPacket implements Packet<ClientGamePacketListener> {

    public ClientboundBlockDestructionPacket(int id, BlockPos pos, int progress) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockDestructionPacket.<init>:(ILnet/minecraft/core/BlockPos;I)V");
    }

    private ClientboundBlockDestructionPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockDestructionPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockDestructionPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundBlockDestructionPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockDestructionPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockDestructionPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockDestructionPacket.getId:()I");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockDestructionPacket.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public ClientboundBlockDestructionPacket() {
    }
}
