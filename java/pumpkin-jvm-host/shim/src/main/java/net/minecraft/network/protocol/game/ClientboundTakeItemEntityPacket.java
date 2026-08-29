package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundTakeItemEntityPacket implements Packet<ClientGamePacketListener> {

    public ClientboundTakeItemEntityPacket(int itemId, int playerId, int amount) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTakeItemEntityPacket.<init>:(III)V");
    }

    private ClientboundTakeItemEntityPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTakeItemEntityPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTakeItemEntityPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundTakeItemEntityPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTakeItemEntityPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTakeItemEntityPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public int getAmount() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTakeItemEntityPacket.getAmount:()I");
    }

    protected ClientboundTakeItemEntityPacket() {
    }
}
