package net.minecraft.world.phys.shapes;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import dev.pumpkin.shim.Unimplemented;

public class EntityCollisionContext implements CollisionContext {

    protected EntityCollisionContext(boolean descending, boolean placement, double entityBottom, ItemStack heldItem, boolean alwaysCollideWithFluid, Entity entity) {
    }

    protected EntityCollisionContext(Entity entity, boolean alwaysCollideWithFluid, boolean placement) {
    }

    public boolean isHoldingItem(Item item) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/EntityCollisionContext.isHoldingItem:(Lnet/minecraft/world/item/Item;)Z");
    }

    public boolean alwaysCollideWithFluid() {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/EntityCollisionContext.alwaysCollideWithFluid:()Z");
    }

    public boolean canStandOnFluid(FluidState fluidStateAbove, FluidState fluid) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/EntityCollisionContext.canStandOnFluid:(Lnet/minecraft/world/level/material/FluidState;Lnet/minecraft/world/level/material/FluidState;)Z");
    }

    public VoxelShape getCollisionShape(BlockState state, CollisionGetter collisionGetter, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/EntityCollisionContext.getCollisionShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/CollisionGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public boolean isDescending() {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/EntityCollisionContext.isDescending:()Z");
    }

    public boolean isAbove(VoxelShape shape, BlockPos pos, boolean defaultValue) {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/EntityCollisionContext.isAbove:(Lnet/minecraft/world/phys/shapes/VoxelShape;Lnet/minecraft/core/BlockPos;Z)Z");
    }

    public Entity getEntity() {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/EntityCollisionContext.getEntity:()Lnet/minecraft/world/entity/Entity;");
    }

    public boolean isPlacement() {
        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/EntityCollisionContext.isPlacement:()Z");
    }

    protected static class Empty extends EntityCollisionContext {

        public Empty(boolean alwaysCollideWithFluid) {
        }

        public boolean isAbove(VoxelShape shape, BlockPos pos, boolean defaultValue) {
            throw Unimplemented.forMember("net/minecraft/world/phys/shapes/EntityCollisionContext$Empty.isAbove:(Lnet/minecraft/world/phys/shapes/VoxelShape;Lnet/minecraft/core/BlockPos;Z)Z");
        }

        protected Empty() {
        }
    }

    public EntityCollisionContext() {
    }
}
