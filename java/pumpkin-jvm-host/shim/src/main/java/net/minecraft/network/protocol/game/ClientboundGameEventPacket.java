package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundGameEventPacket implements Packet<ClientGamePacketListener> {

    public ClientboundGameEventPacket(ClientboundGameEventPacket.Type event, float param) {
    }

    private ClientboundGameEventPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundGameEventPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundGameEventPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundGameEventPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundGameEventPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public static class Type {

        public Type(int id) {
        }

        public Type() {
        }
    }

    public ClientboundGameEventPacket() {
    }
}
