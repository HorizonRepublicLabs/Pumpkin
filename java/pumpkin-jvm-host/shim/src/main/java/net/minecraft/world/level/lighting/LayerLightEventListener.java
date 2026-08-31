package net.minecraft.world.level.lighting;

import net.minecraft.core.BlockPos;
import net.minecraft.core.SectionPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.DataLayer;
import dev.pumpkin.shim.Unimplemented;

public interface LayerLightEventListener extends LightEventListener {

    DataLayer getDataLayerData(final SectionPos pos);

    int getLightValue(final BlockPos pos);

    enum DummyLightLayerEventListener implements LayerLightEventListener {

        INSTANCE;

        public DataLayer getDataLayerData(SectionPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/level/lighting/LayerLightEventListener$DummyLightLayerEventListener.getDataLayerData:(Lnet/minecraft/core/SectionPos;)Lnet/minecraft/world/level/chunk/DataLayer;");
        }

        public int getLightValue(BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/level/lighting/LayerLightEventListener$DummyLightLayerEventListener.getLightValue:(Lnet/minecraft/core/BlockPos;)I");
        }

        public void checkBlock(BlockPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/level/lighting/LayerLightEventListener$DummyLightLayerEventListener.checkBlock:(Lnet/minecraft/core/BlockPos;)V");
        }

        public boolean hasLightWork() {
            throw Unimplemented.forMember("net/minecraft/world/level/lighting/LayerLightEventListener$DummyLightLayerEventListener.hasLightWork:()Z");
        }

        public int runLightUpdates() {
            throw Unimplemented.forMember("net/minecraft/world/level/lighting/LayerLightEventListener$DummyLightLayerEventListener.runLightUpdates:()I");
        }

        public void updateSectionStatus(SectionPos pos, boolean sectionEmpty) {
            throw Unimplemented.forMember("net/minecraft/world/level/lighting/LayerLightEventListener$DummyLightLayerEventListener.updateSectionStatus:(Lnet/minecraft/core/SectionPos;Z)V");
        }

        public void setLightEnabled(ChunkPos pos, boolean enable) {
            throw Unimplemented.forMember("net/minecraft/world/level/lighting/LayerLightEventListener$DummyLightLayerEventListener.setLightEnabled:(Lnet/minecraft/world/level/ChunkPos;Z)V");
        }

        public void propagateLightSources(ChunkPos pos) {
            throw Unimplemented.forMember("net/minecraft/world/level/lighting/LayerLightEventListener$DummyLightLayerEventListener.propagateLightSources:(Lnet/minecraft/world/level/ChunkPos;)V");
        }
    }
}
