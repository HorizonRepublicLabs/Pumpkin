package net.minecraft.world.level.storage;

import com.mojang.serialization.Lifecycle;
import java.util.Set;
import java.util.UUID;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Difficulty;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.WorldDataConfiguration;
import dev.pumpkin.shim.Unimplemented;

public interface WorldData {

    WorldDataConfiguration getDataConfiguration();

    void setDataConfiguration(final WorldDataConfiguration dataConfiguration);

    boolean wasModded();

    Set<String> getKnownServerBrands();

    Set<String> getRemovedFeatureFlags();

    void setModdedInfo(final String serverBrand, final boolean isModded);

    ServerLevelData overworldData();

    LevelSettings getLevelSettings();

    CompoundTag createTag(UUID singlePlayerUUID);

    boolean isHardcore();

    int getVersion();

    String getLevelName();

    GameType getGameType();

    void setGameType(GameType gameType);

    boolean isAllowCommands();

    void setAllowCommands(final boolean allowCommands);

    Difficulty getDifficulty();

    void setDifficulty(final Difficulty difficulty);

    boolean isDifficultyLocked();

    void setDifficultyLocked(final boolean difficultyLocked);

    UUID getSinglePlayerUUID();

    boolean isFlatWorld();

    boolean isDebugWorld();

    Lifecycle worldGenSettingsLifecycle();

    default FeatureFlagSet enabledFeatures() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/WorldData.enabledFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }
}
