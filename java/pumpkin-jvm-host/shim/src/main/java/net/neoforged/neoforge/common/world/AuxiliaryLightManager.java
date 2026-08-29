package net.neoforged.neoforge.common.world;

import net.minecraft.core.BlockPos;

public interface AuxiliaryLightManager {

    void setLightAt(BlockPos pos, int value);

    int getLightAt(BlockPos pos);
}
