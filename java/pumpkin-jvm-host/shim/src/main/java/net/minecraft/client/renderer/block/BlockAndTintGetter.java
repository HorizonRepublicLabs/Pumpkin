package net.minecraft.client.renderer.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndLightGetter;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.ColorResolver;

public interface BlockAndTintGetter extends BlockAndLightGetter {

    CardinalLighting cardinalLighting();

    int getBlockTint(BlockPos pos, ColorResolver color);
}
