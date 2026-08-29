package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.phys.BlockHitResult;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundUseItemOnPacket implements Packet<ServerGamePacketListener> {

    public ServerboundUseItemOnPacket(InteractionHand hand, BlockHitResult blockHit, int sequence) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundUseItemOnPacket.<init>:(Lnet/minecraft/world/InteractionHand;Lnet/minecraft/world/phys/BlockHitResult;I)V");
    }

    private ServerboundUseItemOnPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundUseItemOnPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundUseItemOnPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundUseItemOnPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundUseItemOnPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundUseItemOnPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public ServerboundUseItemOnPacket() {
    }
}
