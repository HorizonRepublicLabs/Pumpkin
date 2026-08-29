package net.minecraft.world.level.storage;

import net.minecraft.world.level.levelgen.WorldDimensions;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import dev.pumpkin.shim.Unimplemented;

public record LevelDataAndDimensions(LevelDataAndDimensions.WorldDataAndGenSettings worldDataAndGenSettings, WorldDimensions.Complete dimensions) {

    public static LevelDataAndDimensions create(WorldData data, WorldGenSettings genSettings, WorldDimensions.Complete dimensions) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/LevelDataAndDimensions.create:(Lnet/minecraft/world/level/storage/WorldData;Lnet/minecraft/world/level/levelgen/WorldGenSettings;Lnet/minecraft/world/level/levelgen/WorldDimensions$Complete;)Lnet/minecraft/world/level/storage/LevelDataAndDimensions;");
    }

    public record WorldDataAndGenSettings(WorldData data, WorldGenSettings genSettings) {
    }
}
