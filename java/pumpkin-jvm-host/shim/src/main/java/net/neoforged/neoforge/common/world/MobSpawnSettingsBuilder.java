package net.neoforged.neoforge.common.world;

import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.MobSpawnSettings;
import dev.pumpkin.shim.Unimplemented;

public class MobSpawnSettingsBuilder extends MobSpawnSettings.Builder {

    public MobSpawnSettingsBuilder(MobSpawnSettings orig) {
    }

    public WeightedList.Builder<MobSpawnSettings.SpawnerData> getSpawner(MobCategory type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/MobSpawnSettingsBuilder.getSpawner:(Lnet/minecraft/world/entity/MobCategory;)Lnet/minecraft/util/random/WeightedList$Builder;");
    }

    public MobSpawnSettings.MobSpawnCost getCost(EntityType<?> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/MobSpawnSettingsBuilder.getCost:(Lnet/minecraft/world/entity/EntityType;)Lnet/minecraft/world/level/biome/MobSpawnSettings$MobSpawnCost;");
    }

    public MobSpawnSettingsBuilder() {
    }
}
