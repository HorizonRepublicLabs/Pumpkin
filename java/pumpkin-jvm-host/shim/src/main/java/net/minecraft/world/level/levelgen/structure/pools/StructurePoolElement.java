package net.minecraft.world.level.levelgen.structure.pools;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.StructureManager;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.templatesystem.LiquidSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public abstract class StructurePoolElement {

    protected StructurePoolElement(StructureTemplatePool.Projection projection) {
    }

    public abstract Vec3i getSize(StructureTemplateManager structureTemplateManager, Rotation rotation);

    public abstract List<StructureTemplate.JigsawBlockInfo> getShuffledJigsawBlocks(StructureTemplateManager structureTemplateManager, BlockPos position, Rotation rotation, RandomSource random);

    public abstract BoundingBox getBoundingBox(StructureTemplateManager structureTemplateManager, BlockPos position, Rotation rotation);

    public abstract boolean place(final StructureTemplateManager structureTemplateManager, final WorldGenLevel level, final StructureManager structureManager, final ChunkGenerator generator, final BlockPos position, final BlockPos referencePos, final Rotation rotation, final BoundingBox chunkBB, final RandomSource random, final LiquidSettings liquidSettings, final boolean keepJigsaws);

    public abstract StructurePoolElementType<?> getType();

    public StructurePoolElement() {
    }
}
