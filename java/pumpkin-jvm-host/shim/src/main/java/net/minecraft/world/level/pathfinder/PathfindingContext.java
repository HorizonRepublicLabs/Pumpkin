package net.minecraft.world.level.pathfinder;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.CollisionGetter;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class PathfindingContext {

    public PathfindingContext(CollisionGetter level, Mob mob) {
    }

    public BlockState getBlockState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/PathfindingContext.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public CollisionGetter level() {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/PathfindingContext.level:()Lnet/minecraft/world/level/CollisionGetter;");
    }

    public PathfindingContext() {
    }
}
