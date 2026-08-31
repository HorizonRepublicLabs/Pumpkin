package net.minecraft.world.level.biome;

import java.util.Map;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import dev.pumpkin.shim.Unimplemented;

public class MobSpawnSettings {

    private MobSpawnSettings(float creatureGenerationProbability, Map<MobCategory, WeightedList<MobSpawnSettings.SpawnerData>> spawners, Map<EntityType<?>, MobSpawnSettings.MobSpawnCost> mobSpawnCosts) {
    }

    public static class Builder {

        public MobSpawnSettings.Builder addMobCharge(EntityType<?> type, double charge, double energyBudget) {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/MobSpawnSettings$Builder.addMobCharge:(Lnet/minecraft/world/entity/EntityType;DD)Lnet/minecraft/world/level/biome/MobSpawnSettings$Builder;");
        }

        public MobSpawnSettings build() {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/MobSpawnSettings$Builder.build:()Lnet/minecraft/world/level/biome/MobSpawnSettings;");
        }

        public Builder() {
        }
    }

    public record MobSpawnCost(double energyBudget, double charge) {
    }

    public record SpawnerData(EntityType<?> type, int minCount, int maxCount) {

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/MobSpawnSettings$SpawnerData.toString:()Ljava/lang/String;");
        }
    }

    public MobSpawnSettings() {
    }
}
