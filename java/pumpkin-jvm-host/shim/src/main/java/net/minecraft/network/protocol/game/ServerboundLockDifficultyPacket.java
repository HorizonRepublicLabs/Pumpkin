package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundLockDifficultyPacket implements Packet<ServerGamePacketListener> {

    public ServerboundLockDifficultyPacket(boolean locked) {
    }

    private ServerboundLockDifficultyPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundLockDifficultyPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundLockDifficultyPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundLockDifficultyPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundLockDifficultyPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public boolean isLocked() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundLockDifficultyPacket.isLocked:()Z");
    }

    public ServerboundLockDifficultyPacket() {
    }
}
