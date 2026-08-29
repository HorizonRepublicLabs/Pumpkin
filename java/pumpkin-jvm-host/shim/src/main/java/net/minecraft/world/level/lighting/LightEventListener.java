package net.minecraft.world.level.lighting;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.ChunkPos;
import dev.pumpkin.shim.Unimplemented;

public interface LightEventListener {

    void checkBlock(BlockPos pos);

    boolean hasLightWork();

    int runLightUpdates();

    default void updateSectionStatus(BlockPos pos, boolean sectionEmpty) {
        throw Unimplemented.forMember("net/minecraft/world/level/lighting/LightEventListener.updateSectionStatus:(Lnet/minecraft/core/BlockPos;Z)V");
    }

    void updateSectionStatus(final SectionPos pos, boolean sectionEmpty);

    void setLightEnabled(ChunkPos pos, boolean enable);

    void propagateLightSources(ChunkPos pos);
}
