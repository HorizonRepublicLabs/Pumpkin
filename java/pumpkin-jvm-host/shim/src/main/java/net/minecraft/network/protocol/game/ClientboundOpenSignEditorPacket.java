package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundOpenSignEditorPacket implements Packet<ClientGamePacketListener> {

    public ClientboundOpenSignEditorPacket(BlockPos pos, boolean isFrontText) {
    }

    private ClientboundOpenSignEditorPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundOpenSignEditorPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundOpenSignEditorPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundOpenSignEditorPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundOpenSignEditorPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundOpenSignEditorPacket.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public ClientboundOpenSignEditorPacket() {
    }
}
