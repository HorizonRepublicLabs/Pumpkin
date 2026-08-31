package net.minecraft.world.entity.ai.navigation;

import java.util.Set;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.level.pathfinder.PathFinder;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public abstract class PathNavigation {

    public PathNavigation(Mob mob, Level level) {
    }

    protected abstract PathFinder createPathFinder(final int maxVisitedNodes);

    public final Path createPath(double x, double y, double z, int reachRange) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.createPath:(DDDI)Lnet/minecraft/world/level/pathfinder/Path;");
    }

    public Path createPath(Stream<BlockPos> positions, int reachRange) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.createPath:(Ljava/util/stream/Stream;I)Lnet/minecraft/world/level/pathfinder/Path;");
    }

    public Path createPath(Set<BlockPos> positions, int reachRange) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.createPath:(Ljava/util/Set;I)Lnet/minecraft/world/level/pathfinder/Path;");
    }

    public Path createPath(BlockPos pos, int reachRange) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.createPath:(Lnet/minecraft/core/BlockPos;I)Lnet/minecraft/world/level/pathfinder/Path;");
    }

    public Path createPath(BlockPos pos, int reachRange, int maxPathLength) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.createPath:(Lnet/minecraft/core/BlockPos;II)Lnet/minecraft/world/level/pathfinder/Path;");
    }

    public Path createPath(Entity target, int reachRange) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.createPath:(Lnet/minecraft/world/entity/Entity;I)Lnet/minecraft/world/level/pathfinder/Path;");
    }

    protected Path createPath(Set<BlockPos> targets, int radiusOffset, boolean above, int reachRange) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.createPath:(Ljava/util/Set;IZI)Lnet/minecraft/world/level/pathfinder/Path;");
    }

    protected Path createPath(Set<BlockPos> targets, int radiusOffset, boolean above, int reachRange, float maxPathLength) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.createPath:(Ljava/util/Set;IZIF)Lnet/minecraft/world/level/pathfinder/Path;");
    }

    public boolean moveTo(double x, double y, double z, double speedModifier) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.moveTo:(DDDD)Z");
    }

    public boolean moveTo(double x, double y, double z, int reachRange, double speedModifier) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.moveTo:(DDDID)Z");
    }

    public boolean moveTo(Entity target, double speedModifier) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.moveTo:(Lnet/minecraft/world/entity/Entity;D)Z");
    }

    public boolean moveTo(Path newPath, double speedModifier) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.moveTo:(Lnet/minecraft/world/level/pathfinder/Path;D)Z");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.tick:()V");
    }

    public boolean isDone() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.isDone:()Z");
    }

    public void stop() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.stop:()V");
    }

    protected abstract Vec3 getTempMobPos();

    protected abstract boolean canUpdatePath();

    public void setCanFloat(boolean canFloat) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/navigation/PathNavigation.setCanFloat:(Z)V");
    }

    public abstract boolean canNavigateGround();

    public PathNavigation() {
    }
}
