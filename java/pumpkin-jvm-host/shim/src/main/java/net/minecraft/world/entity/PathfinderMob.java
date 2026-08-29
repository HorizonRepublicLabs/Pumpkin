package net.minecraft.world.entity;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import dev.pumpkin.shim.Unimplemented;

public abstract class PathfinderMob extends Mob {

    protected PathfinderMob(EntityType<? extends PathfinderMob> type, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/entity/PathfinderMob.<init>:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V");
    }

    public boolean checkSpawnRules(LevelAccessor level, EntitySpawnReason spawnReason) {
        throw Unimplemented.forMember("net/minecraft/world/entity/PathfinderMob.checkSpawnRules:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/world/entity/EntitySpawnReason;)Z");
    }

    public void closeRangeLeashBehaviour(Entity leashHolder) {
        throw Unimplemented.forMember("net/minecraft/world/entity/PathfinderMob.closeRangeLeashBehaviour:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public void whenLeashedTo(Entity leashHolder) {
        throw Unimplemented.forMember("net/minecraft/world/entity/PathfinderMob.whenLeashedTo:(Lnet/minecraft/world/entity/Entity;)V");
    }

    public PathfinderMob() {
    }
}
