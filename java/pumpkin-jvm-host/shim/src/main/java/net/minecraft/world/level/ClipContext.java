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
    }

    public ClipContext(Vec3 from, Vec3 to, ClipContext.Block block, ClipContext.Fluid fluid, CollisionContext collisionContext) {
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
