package net.minecraft.world.level;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public interface CollisionGetter extends BlockGetter {

    WorldBorder getWorldBorder();

    BlockGetter getChunkForCollisions(int chunkX, int chunkZ);

    default boolean isUnobstructed(Entity source, VoxelShape shape) {
        throw Unimplemented.forMember("net/minecraft/world/level/CollisionGetter.isUnobstructed:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/shapes/VoxelShape;)Z");
    }

    default boolean isUnobstructed(BlockState state, BlockPos pos, CollisionContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/CollisionGetter.isUnobstructed:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Z");
    }

    default boolean isUnobstructed(Entity ignore) {
        throw Unimplemented.forMember("net/minecraft/world/level/CollisionGetter.isUnobstructed:(Lnet/minecraft/world/entity/Entity;)Z");
    }

    List<VoxelShape> getEntityCollisions(final Entity source, final AABB testArea);
}
