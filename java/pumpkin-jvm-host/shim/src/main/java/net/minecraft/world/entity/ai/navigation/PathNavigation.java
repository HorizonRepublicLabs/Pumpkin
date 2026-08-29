package net.minecraft.world.entity.ai.navigation;

import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public abstract class PathNavigation {

    public PathNavigation(Mob mob, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.<init>:(Lnet/minecraft/world/entity/Mob;Lnet/minecraft/world/level/Level;)V");
    }

    protected abstract PathFinder createPathFinder(final int maxVisitedNodes);

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.tick:()V");
    }

    protected abstract Vec3 getTempMobPos();

    protected abstract boolean canUpdatePath();

    public abstract boolean canNavigateGround();

    protected PathNavigation() {
    }
}
