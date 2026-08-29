package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public interface LevelWriter {

    boolean setBlock(BlockPos pos, BlockState blockState, int updateFlags, int updateLimit);

    default boolean setBlock(BlockPos pos, BlockState blockState, int updateFlags) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelWriter.setBlock:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z");
    }

    boolean removeBlock(BlockPos pos, boolean movedByPiston);

    boolean destroyBlock(BlockPos pos, boolean dropResources, Entity breaker, int updateLimit);

    default boolean addFreshEntity(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelWriter.addFreshEntity:(Lnet/minecraft/world/entity/Entity;)Z");
    }
}
