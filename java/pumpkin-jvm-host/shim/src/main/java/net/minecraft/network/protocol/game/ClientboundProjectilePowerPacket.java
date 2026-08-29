package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundProjectilePowerPacket implements Packet<ClientGamePacketListener> {

    public ClientboundProjectilePowerPacket(int id, double accelerationPower) {
    }

    private ClientboundProjectilePowerPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundProjectilePowerPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundProjectilePowerPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundProjectilePowerPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundProjectilePowerPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundProjectilePowerPacket.getId:()I");
    }

    public ClientboundProjectilePowerPacket() {
    }
}
