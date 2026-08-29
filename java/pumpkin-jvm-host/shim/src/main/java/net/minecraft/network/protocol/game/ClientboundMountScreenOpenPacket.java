package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundMountScreenOpenPacket implements Packet<ClientGamePacketListener> {

    public ClientboundMountScreenOpenPacket(int containerId, int inventoryColumns, int entityId) {
    }

    private ClientboundMountScreenOpenPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMountScreenOpenPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundMountScreenOpenPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMountScreenOpenPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundMountScreenOpenPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public ClientboundMountScreenOpenPacket() {
    }
}
