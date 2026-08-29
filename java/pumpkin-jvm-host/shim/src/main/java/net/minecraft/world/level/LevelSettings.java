package net.minecraft.world.level;

import net.minecraft.world.Difficulty;
import dev.pumpkin.shim.Unimplemented;

public record LevelSettings(String levelName, GameType gameType, LevelSettings.DifficultySettings difficultySettings, boolean allowCommands, WorldDataConfiguration dataConfiguration, com.mojang.serialization.Lifecycle lifecycle) {

    public LevelSettings(String levelName, GameType gameType, DifficultySettings difficultySettings, boolean allowCommands, WorldDataConfiguration dataConfiguration) {
        this((String) null, (GameType) null, (LevelSettings.DifficultySettings) null, (boolean) false, (WorldDataConfiguration) null, (com.mojang.serialization.Lifecycle) null);
        throw Unimplemented.forMember("net/minecraft/world/level/LevelSettings.<init>:(Ljava/lang/String;Lnet/minecraft/world/level/GameType;Lnet/minecraft/world/level/LevelSettings$DifficultySettings;ZLnet/minecraft/world/level/WorldDataConfiguration;)V");
    }

    public LevelSettings copy() {
        throw Unimplemented.forMember("net/minecraft/world/level/LevelSettings.copy:()Lnet/minecraft/world/level/LevelSettings;");
    }

    public record DifficultySettings(Difficulty difficulty, boolean hardcore, boolean locked) {
    }
}
