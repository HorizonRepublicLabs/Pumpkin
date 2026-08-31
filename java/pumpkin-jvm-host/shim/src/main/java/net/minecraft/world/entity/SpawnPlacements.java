package net.minecraft.world.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.levelgen.Heightmap;
import dev.pumpkin.shim.Unimplemented;

public class SpawnPlacements {

    public static boolean isSpawnPositionOk(EntityType<?> type, LevelReader level, BlockPos blockPos) {
        throw Unimplemented.forMember("net/minecraft/world/entity/SpawnPlacements.isSpawnPositionOk:(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z");
    }

    private record Data(Heightmap.Types heightMap, SpawnPlacementType placement, SpawnPlacements.SpawnPredicate<?> predicate) {
    }

    public interface SpawnPredicate<T extends Entity> {

        boolean test(EntityType<T> type, ServerLevelAccessor level, EntitySpawnReason spawnReason, BlockPos pos, RandomSource random);
    }

    public SpawnPlacements() {
    }
}
