package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundSelectBundleItemPacket(int slotId, int selectedItemIndex) implements Packet<ServerGamePacketListener> {

    private ServerboundSelectBundleItemPacket(FriendlyByteBuf input) {
        this((int) 0, (int) 0);
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSelectBundleItemPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundSelectBundleItemPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSelectBundleItemPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSelectBundleItemPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
