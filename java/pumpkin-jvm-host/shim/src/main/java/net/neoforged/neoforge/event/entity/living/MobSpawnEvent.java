package net.neoforged.neoforge.event.entity.living;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.bus.api.Event;
import net.neoforged.neoforge.event.entity.EntityEvent;
import dev.pumpkin.shim.Unimplemented;

public abstract class MobSpawnEvent extends EntityEvent {

    protected MobSpawnEvent(Mob mob, ServerLevelAccessor level, double x, double y, double z) {
    }

    public Mob getEntity() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/MobSpawnEvent.getEntity:()Lnet/minecraft/world/entity/Mob;");
    }

    public ServerLevelAccessor getLevel() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/MobSpawnEvent.getLevel:()Lnet/minecraft/world/level/ServerLevelAccessor;");
    }

    public double getX() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/MobSpawnEvent.getX:()D");
    }

    public double getY() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/MobSpawnEvent.getY:()D");
    }

    public double getZ() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/MobSpawnEvent.getZ:()D");
    }

    public static class SpawnPlacementCheck extends Event {

        public SpawnPlacementCheck(EntityType<?> entityType, ServerLevelAccessor level, EntitySpawnReason spawnType, BlockPos pos, RandomSource random, boolean defaultResult) {
        }

        public ServerLevelAccessor getLevel() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/MobSpawnEvent$SpawnPlacementCheck.getLevel:()Lnet/minecraft/world/level/ServerLevelAccessor;");
        }

        public BlockPos getPos() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/MobSpawnEvent$SpawnPlacementCheck.getPos:()Lnet/minecraft/core/BlockPos;");
        }

        public RandomSource getRandom() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/entity/living/MobSpawnEvent$SpawnPlacementCheck.getRandom:()Lnet/minecraft/util/RandomSource;");
        }

        public static enum Result {

            SUCCEED, DEFAULT, FAIL
        }

        public SpawnPlacementCheck() {
        }
    }

    public static class PositionCheck extends MobSpawnEvent {

        public PositionCheck(Mob mob, ServerLevelAccessor level, EntitySpawnReason spawnType, BaseSpawner spawner) {
        }

        public static enum Result {

            SUCCEED, DEFAULT, FAIL
        }

        public PositionCheck() {
        }
    }

    public MobSpawnEvent() {
    }
}
