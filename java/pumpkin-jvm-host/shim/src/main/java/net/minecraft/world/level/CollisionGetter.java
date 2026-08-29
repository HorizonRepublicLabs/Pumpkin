package net.minecraft.world.level;

import java.util.List;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.shapes.VoxelShape;

public interface CollisionGetter extends BlockGetter {

    WorldBorder getWorldBorder();

    BlockGetter getChunkForCollisions(int chunkX, int chunkZ);

    List<VoxelShape> getEntityCollisions(final Entity source, final AABB testArea);
}
