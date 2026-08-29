package net.minecraft.world.level.levelgen.structure;

import net.minecraft.util.StringRepresentable;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.biome.MobSpawnSettings;
import dev.pumpkin.shim.Unimplemented;

public record StructureSpawnOverride(StructureSpawnOverride.BoundingBoxType boundingBox, WeightedList<MobSpawnSettings.SpawnerData> spawns) {

    public enum BoundingBoxType implements StringRepresentable {

        PIECE, STRUCTURE;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/StructureSpawnOverride$BoundingBoxType.getSerializedName:()Ljava/lang/String;");
        }
    }
}
