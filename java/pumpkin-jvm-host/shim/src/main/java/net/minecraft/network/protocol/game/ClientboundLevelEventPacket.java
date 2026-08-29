package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundLevelEventPacket implements Packet<ClientGamePacketListener> {

    public ClientboundLevelEventPacket(int type, BlockPos pos, int data, boolean globalEvent) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelEventPacket.<init>:(ILnet/minecraft/core/BlockPos;IZ)V");
    }

    private ClientboundLevelEventPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelEventPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelEventPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundLevelEventPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelEventPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelEventPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public int getType() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelEventPacket.getType:()I");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundLevelEventPacket.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    protected ClientboundLevelEventPacket() {
    }
}
