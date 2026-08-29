package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public class ClipContext {

    public ClipContext(Vec3 from, Vec3 to, ClipContext.Block block, ClipContext.Fluid fluid, Entity entity) {
        throw Unimplemented.forMember("net/minecraft/world/level/ClipContext.<init>:(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/level/ClipContext$Block;Lnet/minecraft/world/level/ClipContext$Fluid;Lnet/minecraft/world/entity/Entity;)V");
    }

    public ClipContext(Vec3 from, Vec3 to, ClipContext.Block block, ClipContext.Fluid fluid, CollisionContext collisionContext) {
        throw Unimplemented.forMember("net/minecraft/world/level/ClipContext.<init>:(Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/level/ClipContext$Block;Lnet/minecraft/world/level/ClipContext$Fluid;Lnet/minecraft/world/phys/shapes/CollisionContext;)V");
    }

    public enum Block implements ClipContext.ShapeGetter {

        COLLIDER, OUTLINE, VISUAL, FALLDAMAGE_RESETTING;

        public VoxelShape get(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/ClipContext$Block.get:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
        }
    }

    public enum Fluid {

        NONE, SOURCE_ONLY, ANY, WATER
    }

    public interface ShapeGetter {

        VoxelShape get(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context);
    }

    public ClipContext() {
    }
}
