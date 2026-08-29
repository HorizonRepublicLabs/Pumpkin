package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundSetCommandMinecartPacket implements Packet<ServerGamePacketListener> {

    public ServerboundSetCommandMinecartPacket(int entity, String command, boolean trackOutput) {
    }

    private ServerboundSetCommandMinecartPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetCommandMinecartPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundSetCommandMinecartPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetCommandMinecartPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetCommandMinecartPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public ServerboundSetCommandMinecartPacket() {
    }
}
