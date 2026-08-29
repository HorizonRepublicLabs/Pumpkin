package net.minecraft.world.level.levelgen;

import net.minecraft.world.level.saveddata.SavedData;
import dev.pumpkin.shim.Unimplemented;

public final class WorldGenSettings extends SavedData {

    public WorldGenSettings(WorldOptions options, WorldDimensions dimensions) {
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldGenSettings.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldGenSettings.toString:()Ljava/lang/String;");
    }

    public WorldGenSettings() {
    }
}
