package net.neoforged.neoforge.common.world;

import java.util.List;
import java.util.Map;
import net.minecraft.core.HolderSet;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.structure.Structure.StructureSettings;
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride;
import net.minecraft.world.level.levelgen.structure.TerrainAdjustment;
import dev.pumpkin.shim.Unimplemented;

public class StructureSettingsBuilder {

    public static StructureSettingsBuilder copyOf(StructureSettings settings) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/StructureSettingsBuilder.copyOf:(Lnet/minecraft/world/level/levelgen/structure/Structure$StructureSettings;)Lnet/neoforged/neoforge/common/world/StructureSettingsBuilder;");
    }

    private StructureSettingsBuilder(HolderSet<Biome> biomes, Map<MobCategory, StructureSpawnOverride> spawnOverrides, GenerationStep.Decoration step, TerrainAdjustment terrainAdaptation) {
    }

    public StructureSettings build() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/StructureSettingsBuilder.build:()Lnet/minecraft/world/level/levelgen/structure/Structure$StructureSettings;");
    }

    public StructureSpawnOverrideBuilder getSpawnOverrides(MobCategory category) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/StructureSettingsBuilder.getSpawnOverrides:(Lnet/minecraft/world/entity/MobCategory;)Lnet/neoforged/neoforge/common/world/StructureSettingsBuilder$StructureSpawnOverrideBuilder;");
    }

    public static class StructureSpawnOverrideBuilder {

        public static StructureSpawnOverrideBuilder copyOf(StructureSpawnOverride override) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/world/StructureSettingsBuilder$StructureSpawnOverrideBuilder.copyOf:(Lnet/minecraft/world/level/levelgen/structure/StructureSpawnOverride;)Lnet/neoforged/neoforge/common/world/StructureSettingsBuilder$StructureSpawnOverrideBuilder;");
        }

        private StructureSpawnOverrideBuilder(StructureSpawnOverride.BoundingBoxType boundingBox, WeightedList<MobSpawnSettings.SpawnerData> spawns) {
        }

        public StructureSpawnOverride.BoundingBoxType getBoundingBox() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/world/StructureSettingsBuilder$StructureSpawnOverrideBuilder.getBoundingBox:()Lnet/minecraft/world/level/levelgen/structure/StructureSpawnOverride$BoundingBoxType;");
        }

        public List<Weighted<MobSpawnSettings.SpawnerData>> getSpawns() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/world/StructureSettingsBuilder$StructureSpawnOverrideBuilder.getSpawns:()Ljava/util/List;");
        }

        public void addSpawn(Weighted<MobSpawnSettings.SpawnerData> spawn) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/world/StructureSettingsBuilder$StructureSpawnOverrideBuilder.addSpawn:(Lnet/minecraft/util/random/Weighted;)V");
        }

        public void addSpawn(MobSpawnSettings.SpawnerData spawn, int weight) {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/world/StructureSettingsBuilder$StructureSpawnOverrideBuilder.addSpawn:(Lnet/minecraft/world/level/biome/MobSpawnSettings$SpawnerData;I)V");
        }

        public StructureSpawnOverride build() {
            throw Unimplemented.forMember("net/neoforged/neoforge/common/world/StructureSettingsBuilder$StructureSpawnOverrideBuilder.build:()Lnet/minecraft/world/level/levelgen/structure/StructureSpawnOverride;");
        }

        public StructureSpawnOverrideBuilder() {
        }
    }

    public StructureSettingsBuilder() {
    }
}
