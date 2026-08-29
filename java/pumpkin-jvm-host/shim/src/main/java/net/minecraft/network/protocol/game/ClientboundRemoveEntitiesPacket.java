package net.minecraft.network.protocol.game;

import it.unimi.dsi.fastutil.ints.IntList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundRemoveEntitiesPacket implements Packet<ClientGamePacketListener> {

    public ClientboundRemoveEntitiesPacket(IntList ids) {
    }

    public ClientboundRemoveEntitiesPacket(int... ids) {
    }

    private ClientboundRemoveEntitiesPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundRemoveEntitiesPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundRemoveEntitiesPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundRemoveEntitiesPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundRemoveEntitiesPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public ClientboundRemoveEntitiesPacket() {
    }
}
