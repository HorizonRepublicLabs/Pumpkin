package net.neoforged.neoforge.common.world;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.chunk.LevelChunk;
import dev.pumpkin.shim.Unimplemented;

public final class LevelChunkAuxiliaryLightManager implements AuxiliaryLightManager {

    public LevelChunkAuxiliaryLightManager(LevelChunk owner) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/LevelChunkAuxiliaryLightManager.<init>:(Lnet/minecraft/world/level/chunk/LevelChunk;)V");
    }

    public void setLightAt(BlockPos pos, int value) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/LevelChunkAuxiliaryLightManager.setLightAt:(Lnet/minecraft/core/BlockPos;I)V");
    }

    public int getLightAt(BlockPos pos) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/world/LevelChunkAuxiliaryLightManager.getLightAt:(Lnet/minecraft/core/BlockPos;)I");
    }

    protected LevelChunkAuxiliaryLightManager() {
    }
}
