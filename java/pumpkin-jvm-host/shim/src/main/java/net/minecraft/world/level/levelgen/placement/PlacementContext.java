package net.minecraft.world.level.levelgen.placement;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.WorldGenerationContext;
import dev.pumpkin.shim.Unimplemented;

public class PlacementContext extends WorldGenerationContext {

    public PlacementContext(WorldGenLevel level, ChunkGenerator generator, Optional<PlacedFeature> topFeature) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/placement/PlacementContext.<init>:(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/chunk/ChunkGenerator;Ljava/util/Optional;)V");
    }

    public int getHeight(Heightmap.Types type, int x, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/placement/PlacementContext.getHeight:(Lnet/minecraft/world/level/levelgen/Heightmap$Types;II)I");
    }

    public BlockState getBlockState(BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/placement/PlacementContext.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public int getMinY() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/placement/PlacementContext.getMinY:()I");
    }

    public WorldGenLevel getLevel() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/placement/PlacementContext.getLevel:()Lnet/minecraft/world/level/WorldGenLevel;");
    }

    protected PlacementContext() {
    }
}
