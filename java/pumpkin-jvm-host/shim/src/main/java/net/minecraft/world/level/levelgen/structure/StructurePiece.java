package net.minecraft.world.level.levelgen.structure;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceSerializationContext;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import dev.pumpkin.shim.Unimplemented;

public abstract class StructurePiece {

    protected StructurePiece(StructurePieceType type, int genDepth, BoundingBox boundingBox) {
    }

    public StructurePiece(StructurePieceType type, CompoundTag tag) {
    }

    public final CompoundTag createTag(StructurePieceSerializationContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/StructurePiece.createTag:(Lnet/minecraft/world/level/levelgen/structure/pieces/StructurePieceSerializationContext;)Lnet/minecraft/nbt/CompoundTag;");
    }

    protected abstract void addAdditionalSaveData(StructurePieceSerializationContext context, CompoundTag tag);

    public abstract void postProcess(final WorldGenLevel level, final StructureManager structureManager, final ChunkGenerator generator, final RandomSource random, final BoundingBox chunkBB, final ChunkPos chunkPos, final BlockPos referencePos);

    public BoundingBox getBoundingBox() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/StructurePiece.getBoundingBox:()Lnet/minecraft/world/level/levelgen/structure/BoundingBox;");
    }

    public int getGenDepth() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/StructurePiece.getGenDepth:()I");
    }

    public StructurePieceType getType() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/StructurePiece.getType:()Lnet/minecraft/world/level/levelgen/structure/pieces/StructurePieceType;");
    }

    public abstract static class BlockSelector {

        public abstract void next(RandomSource random, int worldX, int worldY, int worldZ, boolean isEdge);

        public BlockSelector() {
        }
    }

    public StructurePiece() {
    }
}
