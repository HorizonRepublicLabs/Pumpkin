package net.minecraft.world.phys.shapes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;

public interface CollisionContext {

    boolean isDescending();

    boolean isAbove(final VoxelShape shape, final BlockPos pos, final boolean defaultValue);

    boolean isHoldingItem(final Item item);

    boolean alwaysCollideWithFluid();

    boolean canStandOnFluid(final FluidState fluidStateAbove, final FluidState fluid);

    VoxelShape getCollisionShape(BlockState state, CollisionGetter collisionGetter, BlockPos pos);
}
