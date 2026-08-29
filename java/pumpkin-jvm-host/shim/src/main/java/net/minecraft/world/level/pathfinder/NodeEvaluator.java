package net.minecraft.world.level.pathfinder;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.PathNavigationRegion;
import dev.pumpkin.shim.Unimplemented;

public abstract class NodeEvaluator {

    public void prepare(PathNavigationRegion level, Mob entity) {
        throw Unimplemented.forMember("net/minecraft/world/level/pathfinder/NodeEvaluator.prepare:(Lnet/minecraft/world/level/PathNavigationRegion;Lnet/minecraft/world/entity/Mob;)V");
    }

    public abstract Node getStart();

    public abstract Target getTarget(double x, double y, double z);

    public abstract int getNeighbors(Node[] neighbors, Node pos);

    public abstract PathType getPathTypeOfMob(PathfindingContext context, int x, int y, int z, Mob mob);

    public abstract PathType getPathType(PathfindingContext context, int x, int y, int z);

    protected NodeEvaluator() {
    }
}
