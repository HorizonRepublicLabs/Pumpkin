package net.minecraft.world.level.levelgen.structure;

import com.mojang.datafixers.util.Either;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.WorldgenRandom;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import dev.pumpkin.shim.Unimplemented;

public abstract class Structure {

    protected Structure(Structure.StructureSettings settings) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/Structure.<init>:(Lnet/minecraft/world/level/levelgen/structure/Structure$StructureSettings;)V");
    }

    protected abstract Optional<Structure.GenerationStub> findGenerationPoint(final Structure.GenerationContext context);

    public abstract StructureType<?> type();

    public record GenerationContext(RegistryAccess registryAccess, ChunkGenerator chunkGenerator, BiomeSource biomeSource, RandomState randomState, StructureTemplateManager structureTemplateManager, WorldgenRandom random, long seed, ChunkPos chunkPos, LevelHeightAccessor heightAccessor, Predicate<Holder<Biome>> validBiome) {

        public GenerationContext(RegistryAccess registryAccess, ChunkGenerator chunkGenerator, BiomeSource biomeSource, RandomState randomState, StructureTemplateManager structureTemplateManager, long seed, ChunkPos chunkPos, LevelHeightAccessor heightAccessor, Predicate<Holder<Biome>> validBiome) {
            this((RegistryAccess) null, (ChunkGenerator) null, (BiomeSource) null, (RandomState) null, (StructureTemplateManager) null, (WorldgenRandom) null, (long) 0L, (ChunkPos) null, (LevelHeightAccessor) null, (Predicate<Holder<Biome>>) null);
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/Structure$GenerationContext.<init>:(Lnet/minecraft/core/RegistryAccess;Lnet/minecraft/world/level/chunk/ChunkGenerator;Lnet/minecraft/world/level/biome/BiomeSource;Lnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/levelgen/structure/templatesystem/StructureTemplateManager;JLnet/minecraft/world/level/ChunkPos;Lnet/minecraft/world/level/LevelHeightAccessor;Ljava/util/function/Predicate;)V");
        }
    }

    public record GenerationStub(BlockPos position, Either<Consumer<StructurePiecesBuilder>, StructurePiecesBuilder> generator) {

        public GenerationStub(BlockPos position, Consumer<StructurePiecesBuilder> generator) {
            this((BlockPos) null, (Either<Consumer<StructurePiecesBuilder>, StructurePiecesBuilder>) null);
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/Structure$GenerationStub.<init>:(Lnet/minecraft/core/BlockPos;Ljava/util/function/Consumer;)V");
        }
    }

    public record StructureSettings(HolderSet<Biome> biomes, Map<MobCategory, StructureSpawnOverride> spawnOverrides, GenerationStep.Decoration step, TerrainAdjustment terrainAdaptation) {

        public StructureSettings(HolderSet<Biome> biomes) {
            this((HolderSet<Biome>) null, (Map<MobCategory, StructureSpawnOverride>) null, (GenerationStep.Decoration) null, (TerrainAdjustment) null);
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/Structure$StructureSettings.<init>:(Lnet/minecraft/core/HolderSet;)V");
        }

        public static class Builder {

            public Builder(HolderSet<Biome> biomes) {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/Structure$StructureSettings$Builder.<init>:(Lnet/minecraft/core/HolderSet;)V");
            }

            public Structure.StructureSettings build() {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/Structure$StructureSettings$Builder.build:()Lnet/minecraft/world/level/levelgen/structure/Structure$StructureSettings;");
            }

            public Builder() {
            }
        }
    }

    public Structure() {
    }
}
