package net.minecraft.world.level.levelgen;

import com.google.common.collect.ImmutableSet;
import java.util.Map;
import java.util.Optional;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.storage.PrimaryLevelData;
import dev.pumpkin.shim.Unimplemented;

public record WorldDimensions(Map<ResourceKey<LevelStem>, LevelStem> dimensions) {

    public WorldDimensions(Registry<LevelStem> registry) {
        this((Map<ResourceKey<LevelStem>, LevelStem>) null);
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldDimensions.<init>:(Lnet/minecraft/core/Registry;)V");
    }

    public Optional<LevelStem> get(ResourceKey<LevelStem> key) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldDimensions.get:(Lnet/minecraft/resources/ResourceKey;)Ljava/util/Optional;");
    }

    public ImmutableSet<ResourceKey<Level>> levels() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldDimensions.levels:()Lcom/google/common/collect/ImmutableSet;");
    }

    public WorldDimensions.Complete bake(Registry<LevelStem> baseDimensions) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldDimensions.bake:(Lnet/minecraft/core/Registry;)Lnet/minecraft/world/level/levelgen/WorldDimensions$Complete;");
    }

    public record Complete(Registry<LevelStem> dimensions, PrimaryLevelData.SpecialWorldProperty specialWorldProperty) {
    }
}
