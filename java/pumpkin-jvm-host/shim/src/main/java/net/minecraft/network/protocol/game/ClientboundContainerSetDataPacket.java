package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundContainerSetDataPacket implements Packet<ClientGamePacketListener> {

    public ClientboundContainerSetDataPacket(int containerId, int id, int value) {
    }

    private ClientboundContainerSetDataPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundContainerSetDataPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundContainerSetDataPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundContainerSetDataPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundContainerSetDataPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public int getId() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundContainerSetDataPacket.getId:()I");
    }

    public ClientboundContainerSetDataPacket() {
    }
}
