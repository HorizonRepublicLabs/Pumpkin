package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.entity.JigsawBlockEntity;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundSetJigsawBlockPacket implements Packet<ServerGamePacketListener> {

    public ServerboundSetJigsawBlockPacket(BlockPos blockPos, Identifier name, Identifier target, Identifier pool, String finalState, JigsawBlockEntity.JointType joint, int selectionPriority, int placementPriority) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetJigsawBlockPacket.<init>:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/Identifier;Ljava/lang/String;Lnet/minecraft/world/level/block/entity/JigsawBlockEntity$JointType;II)V");
    }

    private ServerboundSetJigsawBlockPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetJigsawBlockPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetJigsawBlockPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundSetJigsawBlockPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetJigsawBlockPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetJigsawBlockPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetJigsawBlockPacket.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public Identifier getName() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetJigsawBlockPacket.getName:()Lnet/minecraft/resources/Identifier;");
    }

    public Identifier getTarget() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetJigsawBlockPacket.getTarget:()Lnet/minecraft/resources/Identifier;");
    }

    public ServerboundSetJigsawBlockPacket() {
    }
}
