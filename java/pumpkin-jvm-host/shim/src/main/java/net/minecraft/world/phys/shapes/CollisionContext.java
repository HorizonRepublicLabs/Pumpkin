package net.minecraft.world.phys.shapes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import dev.pumpkin.shim.Unimplemented;

public interface CollisionContext {

    static CollisionContext empty() {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/CollisionContext.empty:()Lnet/minecraft/world/phys/shapes/CollisionContext;");
    }

    static CollisionContext of(Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/CollisionContext.of:(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/world/phys/shapes/CollisionContext;");
    }

    static CollisionContext of(Entity entity, boolean alwaysCollideWithFluid) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/CollisionContext.of:(Lnet/minecraft/world/entity/Entity;Z)Lnet/minecraft/world/phys/shapes/CollisionContext;");
    }

    boolean isDescending();

    boolean isAbove(final VoxelShape shape, final BlockPos pos, final boolean defaultValue);

    boolean isHoldingItem(final Item item);

    boolean alwaysCollideWithFluid();

    boolean canStandOnFluid(final FluidState fluidStateAbove, final FluidState fluid);

    VoxelShape getCollisionShape(BlockState state, CollisionGetter collisionGetter, BlockPos pos);
}
