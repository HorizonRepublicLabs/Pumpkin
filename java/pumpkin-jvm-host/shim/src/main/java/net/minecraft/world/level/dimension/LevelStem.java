package net.minecraft.world.level.dimension;

import net.minecraft.core.Holder;
import net.minecraft.world.level.chunk.ChunkGenerator;

public record // NeoForge: add neoforge:seed_override field to dimension jsons
LevelStem(// NeoForge: add neoforge:seed_override field to dimension jsons
Holder<DimensionType> type, // NeoForge: add neoforge:seed_override field to dimension jsons
ChunkGenerator generator, java.util.OptionalLong seedOverride) {

    public LevelStem(Holder<DimensionType> type, ChunkGenerator generator) {
        this((Holder<DimensionType>) null, (ChunkGenerator) null, (java.util.OptionalLong) null);
    }
}
