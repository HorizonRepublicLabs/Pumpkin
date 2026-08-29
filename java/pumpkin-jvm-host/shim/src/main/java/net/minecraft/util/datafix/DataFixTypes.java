package net.minecraft.util.datafix;

import com.mojang.datafixers.DataFixer;
import com.mojang.serialization.Dynamic;
import net.minecraft.nbt.CompoundTag;
import dev.pumpkin.shim.Unimplemented;

public enum DataFixTypes {

    LEVEL,
    LEVEL_SUMMARY,
    PLAYER,
    CHUNK,
    HOTBAR,
    OPTIONS,
    STRUCTURE,
    STATS,
    SAVED_DATA_COMMAND_STORAGE,
    SAVED_DATA_CUSTOM_BOSS_EVENTS,
    SAVED_DATA_ENDER_DRAGON_FIGHT,
    SAVED_DATA_GAME_RULES,
    SAVED_DATA_FORCED_CHUNKS,
    SAVED_DATA_MAP_DATA,
    SAVED_DATA_MAP_INDEX,
    SAVED_DATA_RAIDS,
    SAVED_DATA_RANDOM_SEQUENCES,
    SAVED_DATA_SCHEDULED_EVENTS,
    SAVED_DATA_SCOREBOARD,
    SAVED_DATA_STOPWATCHES,
    SAVED_DATA_STRUCTURE_FEATURE_INDICES,
    SAVED_DATA_WANDERING_TRADER,
    SAVED_DATA_WEATHER,
    SAVED_DATA_WORLD_BORDER,
    SAVED_DATA_WORLD_CLOCKS,
    SAVED_DATA_WORLD_GEN_SETTINGS,
    ADVANCEMENTS,
    POI_CHUNK,
    WORLD_GEN_SETTINGS,
    ENTITY_CHUNK,
    DEBUG_PROFILE;

    public <T> Dynamic<T> update(DataFixer fixerUpper, Dynamic<T> input, int fromVersion, int toVersion) {
        throw Unimplemented.forMember("net/minecraft/util/datafix/DataFixTypes.update:(Lcom/mojang/datafixers/DataFixer;Lcom/mojang/serialization/Dynamic;II)Lcom/mojang/serialization/Dynamic;");
    }

    public CompoundTag update(DataFixer fixer, CompoundTag tag, int fromVersion, int toVersion) {
        throw Unimplemented.forMember("net/minecraft/util/datafix/DataFixTypes.update:(Lcom/mojang/datafixers/DataFixer;Lnet/minecraft/nbt/CompoundTag;II)Lnet/minecraft/nbt/CompoundTag;");
    }
}
