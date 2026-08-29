package net.minecraft.world.level.storage;

import net.minecraft.core.GlobalPos;
import net.minecraft.world.Difficulty;

public interface LevelData {

    LevelData.RespawnData getRespawnData();

    long getGameTime();

    boolean isHardcore();

    Difficulty getDifficulty();

    boolean isDifficultyLocked();

    record RespawnData(GlobalPos globalPos, float yaw, float pitch) {
    }
}
