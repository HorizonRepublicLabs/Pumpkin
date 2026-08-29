package net.minecraft.network.protocol.game;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.StructureBlockEntity;
import net.minecraft.world.level.block.state.properties.StructureMode;
import dev.pumpkin.shim.Unimplemented;

public class ServerboundSetStructureBlockPacket implements Packet<ServerGamePacketListener> {

    public ServerboundSetStructureBlockPacket(BlockPos pos, StructureBlockEntity.UpdateType updateType, StructureMode mode, String name, BlockPos offset, Vec3i size, Mirror mirror, Rotation rotation, String data, boolean ignoreEntities, boolean strict, boolean showAir, boolean showBoundingBox, float integrity, long seed) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetStructureBlockPacket.<init>:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/StructureBlockEntity$UpdateType;Lnet/minecraft/world/level/block/state/properties/StructureMode;Ljava/lang/String;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Vec3i;Lnet/minecraft/world/level/block/Mirror;Lnet/minecraft/world/level/block/Rotation;Ljava/lang/String;ZZZZFJ)V");
    }

    private ServerboundSetStructureBlockPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetStructureBlockPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetStructureBlockPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ServerboundSetStructureBlockPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetStructureBlockPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetStructureBlockPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public BlockPos getPos() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetStructureBlockPacket.getPos:()Lnet/minecraft/core/BlockPos;");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetStructureBlockPacket.getName:()Ljava/lang/String;");
    }

    public Vec3i getSize() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetStructureBlockPacket.getSize:()Lnet/minecraft/core/Vec3i;");
    }

    public long getSeed() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetStructureBlockPacket.getSeed:()J");
    }

    public ServerboundSetStructureBlockPacket() {
    }
}
