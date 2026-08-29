package net.minecraft.world.level.storage;

import com.mojang.serialization.Lifecycle;
import java.util.Set;
import java.util.UUID;
import net.minecraft.CrashReportCategory;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Difficulty;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.LevelSettings;
import net.minecraft.world.level.WorldDataConfiguration;
import dev.pumpkin.shim.Unimplemented;

public class PrimaryLevelData implements ServerLevelData, WorldData {

    private PrimaryLevelData(UUID singlePlayerUUID, boolean wasModded, LevelData.RespawnData respawnData, long gameTime, int version, boolean initialized, Set<String> knownServerBrands, Set<String> removedFeatureFlags, LevelSettings settings, PrimaryLevelData.SpecialWorldProperty specialWorldProperty, Lifecycle worldGenSettingsLifecycle) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.<init>:(Ljava/util/UUID;ZLnet/minecraft/world/level/storage/LevelData$RespawnData;JIZLjava/util/Set;Ljava/util/Set;Lnet/minecraft/world/level/LevelSettings;Lnet/minecraft/world/level/storage/PrimaryLevelData$SpecialWorldProperty;Lcom/mojang/serialization/Lifecycle;)V");
    }

    public PrimaryLevelData(LevelSettings levelSettings, PrimaryLevelData.SpecialWorldProperty specialWorldProperty, Lifecycle lifecycle) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.<init>:(Lnet/minecraft/world/level/LevelSettings;Lnet/minecraft/world/level/storage/PrimaryLevelData$SpecialWorldProperty;Lcom/mojang/serialization/Lifecycle;)V");
    }

    public CompoundTag createTag(UUID singlePlayerUUID) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.createTag:(Ljava/util/UUID;)Lnet/minecraft/nbt/CompoundTag;");
    }

    public LevelData.RespawnData getRespawnData() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.getRespawnData:()Lnet/minecraft/world/level/storage/LevelData$RespawnData;");
    }

    public long getGameTime() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.getGameTime:()J");
    }

    public UUID getSinglePlayerUUID() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.getSinglePlayerUUID:()Ljava/util/UUID;");
    }

    public void setGameTime(long time) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.setGameTime:(J)V");
    }

    public void setSpawn(LevelData.RespawnData respawnData) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.setSpawn:(Lnet/minecraft/world/level/storage/LevelData$RespawnData;)V");
    }

    public String getLevelName() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.getLevelName:()Ljava/lang/String;");
    }

    public int getVersion() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.getVersion:()I");
    }

    public GameType getGameType() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.getGameType:()Lnet/minecraft/world/level/GameType;");
    }

    public void setGameType(GameType gameType) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.setGameType:(Lnet/minecraft/world/level/GameType;)V");
    }

    public boolean isHardcore() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.isHardcore:()Z");
    }

    public boolean isAllowCommands() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.isAllowCommands:()Z");
    }

    public void setAllowCommands(boolean allowCommands) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.setAllowCommands:(Z)V");
    }

    public boolean isInitialized() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.isInitialized:()Z");
    }

    public void setInitialized(boolean initialized) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.setInitialized:(Z)V");
    }

    public Difficulty getDifficulty() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.getDifficulty:()Lnet/minecraft/world/Difficulty;");
    }

    public void setDifficulty(Difficulty difficulty) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.setDifficulty:(Lnet/minecraft/world/Difficulty;)V");
    }

    public boolean isDifficultyLocked() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.isDifficultyLocked:()Z");
    }

    public void setDifficultyLocked(boolean difficultyLocked) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.setDifficultyLocked:(Z)V");
    }

    public void fillCrashReportCategory(CrashReportCategory category, LevelHeightAccessor levelHeightAccessor) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.fillCrashReportCategory:(Lnet/minecraft/CrashReportCategory;Lnet/minecraft/world/level/LevelHeightAccessor;)V");
    }

    public boolean isFlatWorld() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.isFlatWorld:()Z");
    }

    public boolean isDebugWorld() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.isDebugWorld:()Z");
    }

    public Lifecycle worldGenSettingsLifecycle() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.worldGenSettingsLifecycle:()Lcom/mojang/serialization/Lifecycle;");
    }

    public WorldDataConfiguration getDataConfiguration() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.getDataConfiguration:()Lnet/minecraft/world/level/WorldDataConfiguration;");
    }

    public void setDataConfiguration(WorldDataConfiguration dataConfiguration) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.setDataConfiguration:(Lnet/minecraft/world/level/WorldDataConfiguration;)V");
    }

    public void setModdedInfo(String serverBrand, boolean isModded) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.setModdedInfo:(Ljava/lang/String;Z)V");
    }

    public boolean wasModded() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.wasModded:()Z");
    }

    public Set<String> getKnownServerBrands() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.getKnownServerBrands:()Ljava/util/Set;");
    }

    public Set<String> getRemovedFeatureFlags() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.getRemovedFeatureFlags:()Ljava/util/Set;");
    }

    public ServerLevelData overworldData() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.overworldData:()Lnet/minecraft/world/level/storage/ServerLevelData;");
    }

    public LevelSettings getLevelSettings() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.getLevelSettings:()Lnet/minecraft/world/level/LevelSettings;");
    }

    public enum SpecialWorldProperty {

        NONE, FLAT, DEBUG
    }

    public float getDayTimeFraction() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.getDayTimeFraction:()F");
    }

    public float getDayTimePerTick() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.getDayTimePerTick:()F");
    }

    public void setDayTimeFraction(float dayTimeFraction) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.setDayTimeFraction:(F)V");
    }

    public void setDayTimePerTick(float dayTimePerTick) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/PrimaryLevelData.setDayTimePerTick:(F)V");
    }

    protected PrimaryLevelData() {
    }
}
