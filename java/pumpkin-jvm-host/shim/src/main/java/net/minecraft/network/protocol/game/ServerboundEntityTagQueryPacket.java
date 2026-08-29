package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundEntityTagQueryPacket implements Packet<ServerGamePacketListener> {

    public ServerboundEntityTagQueryPacket(int transactionId, int entityId) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundEntityTagQueryPacket.<init>:(II)V");
    }

    private ServerboundEntityTagQueryPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundEntityTagQueryPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundEntityTagQueryPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundEntityTagQueryPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundEntityTagQueryPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundEntityTagQueryPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    protected ServerboundEntityTagQueryPacket() {
    }
}
