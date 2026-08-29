package net.minecraft.world.level;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;

public interface ServerLevelAccessor extends LevelAccessor {

    ServerLevel getLevel();

    DifficultyInstance getCurrentDifficultyAt(BlockPos pos);
}
