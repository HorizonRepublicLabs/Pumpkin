package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.InteractionHand;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundUseItemPacket implements Packet<ServerGamePacketListener> {

    public ServerboundUseItemPacket(InteractionHand hand, int sequence, float yRot, float xRot) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundUseItemPacket.<init>:(Lnet/minecraft/world/InteractionHand;IFF)V");
    }

    private ServerboundUseItemPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundUseItemPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundUseItemPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundUseItemPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundUseItemPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundUseItemPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public ServerboundUseItemPacket() {
    }
}
