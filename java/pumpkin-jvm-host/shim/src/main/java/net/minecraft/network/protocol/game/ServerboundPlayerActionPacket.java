package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundPlayerActionPacket implements Packet<ServerGamePacketListener> {

    public ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.Action action, BlockPos pos, Direction direction, int sequence) {
    }

    public ServerboundPlayerActionPacket(ServerboundPlayerActionPacket.Action action, BlockPos pos, Direction direction) {
    }

    private ServerboundPlayerActionPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerActionPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundPlayerActionPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerActionPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerActionPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlayerActionPacket.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public enum Action {

        START_DESTROY_BLOCK,
        ABORT_DESTROY_BLOCK,
        STOP_DESTROY_BLOCK,
        DROP_ALL_ITEMS,
        DROP_ITEM,
        RELEASE_USE_ITEM,
        SWAP_ITEM_WITH_OFFHAND,
        STAB
    }

    public ServerboundPlayerActionPacket() {
    }
}
