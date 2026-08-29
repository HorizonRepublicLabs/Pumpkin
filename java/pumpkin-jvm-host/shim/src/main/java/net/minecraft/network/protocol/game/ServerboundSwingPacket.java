package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.InteractionHand;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundSwingPacket implements Packet<ServerGamePacketListener> {

    public ServerboundSwingPacket(InteractionHand hand) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSwingPacket.<init>:(Lnet/minecraft/world/InteractionHand;)V");
    }

    private ServerboundSwingPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSwingPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSwingPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundSwingPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSwingPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSwingPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    protected ServerboundSwingPacket() {
    }
}
