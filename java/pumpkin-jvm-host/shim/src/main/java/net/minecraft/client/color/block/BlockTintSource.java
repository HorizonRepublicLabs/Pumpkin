package net.minecraft.client.color.block;

import net.minecraft.world.level.block.state.BlockState;

public interface BlockTintSource {

    int color(BlockState state);
}
