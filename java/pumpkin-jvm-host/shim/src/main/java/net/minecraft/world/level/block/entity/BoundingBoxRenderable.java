package net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Vec3i;

public interface BoundingBoxRenderable {

    BoundingBoxRenderable.Mode renderMode();

    BoundingBoxRenderable.RenderableBox getRenderableBox();

    enum Mode {

        NONE, BOX, BOX_AND_INVISIBLE_BLOCKS
    }

    record RenderableBox(BlockPos localPos, Vec3i size) {
    }
}
