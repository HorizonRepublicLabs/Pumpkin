package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundSignUpdatePacket implements Packet<ServerGamePacketListener> {

    public ServerboundSignUpdatePacket(BlockPos pos, boolean isFrontText, String line0, String line1, String line2, String line3) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSignUpdatePacket.<init>:(Lnet/minecraft/core/BlockPos;ZLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
    }

    private ServerboundSignUpdatePacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSignUpdatePacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSignUpdatePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundSignUpdatePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSignUpdatePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSignUpdatePacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSignUpdatePacket.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public ServerboundSignUpdatePacket() {
    }
}
