package net.minecraft.world.level.dimension;

import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.StringRepresentable;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.attribute.EnvironmentAttributeMap;
import net.minecraft.world.clock.WorldClock;
import net.minecraft.world.level.CardinalLighting;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.timeline.Timeline;
import dev.pumpkin.shim.Unimplemented;

public record DimensionType(boolean hasFixedTime, boolean hasSkyLight, boolean hasCeiling, boolean hasEnderDragonFight, double coordinateScale, int minY, int height, int logicalHeight, HolderSet<Block> infiniburn, float ambientLight, DimensionType.MonsterSettings monsterSettings, DimensionType.Skybox skybox, CardinalLighting.Type cardinalLightType, EnvironmentAttributeMap attributes, HolderSet<Timeline> timelines, Optional<Holder<WorldClock>> defaultClock) {

    public record MonsterSettings(IntProvider monsterSpawnLightTest, int monsterSpawnBlockLightLimit) {
    }

    public enum Skybox implements StringRepresentable {

        NONE, OVERWORLD, END;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/dimension/DimensionType$Skybox.getSerializedName:()Ljava/lang/String;");
        }
    }
}
