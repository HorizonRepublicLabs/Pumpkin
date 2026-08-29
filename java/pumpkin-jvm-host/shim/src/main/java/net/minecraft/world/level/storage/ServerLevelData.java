package net.minecraft.world.level.storage;

import net.minecraft.CrashReportCategory;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelHeightAccessor;
import dev.pumpkin.shim.Unimplemented;

public interface ServerLevelData extends WritableLevelData {

    String getLevelName();

    default void fillCrashReportCategory(CrashReportCategory category, LevelHeightAccessor levelHeightAccessor) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/ServerLevelData.fillCrashReportCategory:(Lnet/minecraft/CrashReportCategory;Lnet/minecraft/world/level/LevelHeightAccessor;)V");
    }

    GameType getGameType();

    boolean isInitialized();

    void setInitialized(boolean initialized);

    boolean isAllowCommands();

    void setAllowCommands(boolean allowCommands);

    void setGameType(GameType gameType);

    void setGameTime(final long time);

    float getDayTimeFraction();

    float getDayTimePerTick();

    void setDayTimeFraction(float dayTimeFraction);

    void setDayTimePerTick(float dayTimePerTick);
}
