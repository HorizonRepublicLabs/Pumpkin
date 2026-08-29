package net.minecraft.world.level;

import java.util.List;
import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public interface CommonLevelAccessor extends LevelReader, LevelSimulatedRW, EntityGetter {

    default <T extends BlockEntity> Optional<T> getBlockEntity(BlockPos pos, BlockEntityType<T> type) {
        throw Unimplemented.forMember("net/minecraft/world/level/CommonLevelAccessor.getBlockEntity:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntityType;)Ljava/util/Optional;");
    }

    default List<VoxelShape> getEntityCollisions(Entity source, AABB testArea) {
        throw Unimplemented.forMember("net/minecraft/world/level/CommonLevelAccessor.getEntityCollisions:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;");
    }

    default boolean isUnobstructed(Entity source, VoxelShape shape) {
        throw Unimplemented.forMember("net/minecraft/world/level/CommonLevelAccessor.isUnobstructed:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/shapes/VoxelShape;)Z");
    }

    default BlockPos getHeightmapPos(Heightmap.Types type, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/CommonLevelAccessor.getHeightmapPos:(Lnet/minecraft/world/level/levelgen/Heightmap$Types;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/BlockPos;");
    }
}
