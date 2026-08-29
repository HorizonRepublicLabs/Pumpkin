package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundSetCarriedItemPacket implements Packet<ServerGamePacketListener> {

    public ServerboundSetCarriedItemPacket(int slot) {
    }

    private ServerboundSetCarriedItemPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetCarriedItemPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundSetCarriedItemPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetCarriedItemPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetCarriedItemPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public ServerboundSetCarriedItemPacket() {
    }
}
