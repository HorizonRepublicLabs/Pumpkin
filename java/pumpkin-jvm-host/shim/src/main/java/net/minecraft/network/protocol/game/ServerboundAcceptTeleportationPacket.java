package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundAcceptTeleportationPacket implements Packet<ServerGamePacketListener> {

    public ServerboundAcceptTeleportationPacket(int id) {
    }

    private ServerboundAcceptTeleportationPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundAcceptTeleportationPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundAcceptTeleportationPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundAcceptTeleportationPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundAcceptTeleportationPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundAcceptTeleportationPacket.getId:()I");
    }

    public ServerboundAcceptTeleportationPacket() {
    }
}
