package net.minecraft.world.level.pathfinder;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.PathNavigationRegion;
import dev.pumpkin.shim.Unimplemented;

public class WalkNodeEvaluator extends NodeEvaluator {

    public void prepare(PathNavigationRegion level, Mob entity) {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/WalkNodeEvaluator.prepare:(Lnet/minecraft/world/level/PathNavigationRegion;Lnet/minecraft/world/entity/Mob;)V");
    }

    public void done() {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/WalkNodeEvaluator.done:()V");
    }

    public Node getStart() {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/WalkNodeEvaluator.getStart:()Lnet/minecraft/world/level/pathfinder/Node;");
    }

    public Target getTarget(double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/WalkNodeEvaluator.getTarget:(DDD)Lnet/minecraft/world/level/pathfinder/Target;");
    }

    public int getNeighbors(Node[] neighbors, Node pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/WalkNodeEvaluator.getNeighbors:([Lnet/minecraft/world/level/pathfinder/Node;Lnet/minecraft/world/level/pathfinder/Node;)I");
    }

    public PathType getPathTypeOfMob(PathfindingContext context, int x, int y, int z, Mob mob) {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/WalkNodeEvaluator.getPathTypeOfMob:(Lnet/minecraft/world/level/pathfinder/PathfindingContext;IIILnet/minecraft/world/entity/Mob;)Lnet/minecraft/world/level/pathfinder/PathType;");
    }

    public PathType getPathType(PathfindingContext context, int x, int y, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/WalkNodeEvaluator.getPathType:(Lnet/minecraft/world/level/pathfinder/PathfindingContext;III)Lnet/minecraft/world/level/pathfinder/PathType;");
    }

    public static PathType getPathTypeStatic(Mob mob, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/WalkNodeEvaluator.getPathTypeStatic:(Lnet/minecraft/world/entity/Mob;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/pathfinder/PathType;");
    }

    public static PathType getPathTypeStatic(PathfindingContext context, BlockPos.MutableBlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/WalkNodeEvaluator.getPathTypeStatic:(Lnet/minecraft/world/level/pathfinder/PathfindingContext;Lnet/minecraft/core/BlockPos$MutableBlockPos;)Lnet/minecraft/world/level/pathfinder/PathType;");
    }

    public WalkNodeEvaluator() {
    }
}
