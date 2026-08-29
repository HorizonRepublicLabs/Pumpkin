package net.minecraft.world.level.lighting;

import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelHeightAccessor;
import dev.pumpkin.shim.Unimplemented;

public class ChunkSkyLightSources {

    public ChunkSkyLightSources(LevelHeightAccessor level) {
        throw Unimplemented.forMember("net/minecraft/world/level/lighting/ChunkSkyLightSources.<init>:(Lnet/minecraft/world/level/LevelHeightAccessor;)V");
    }

    public boolean update(BlockGetter level, int x, int y, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/lighting/ChunkSkyLightSources.update:(Lnet/minecraft/world/level/BlockGetter;III)Z");
    }

    private void set(int index, int value) {
        throw Unimplemented.forMember("net/minecraft/world/level/lighting/ChunkSkyLightSources.set:(II)V");
    }

    private int get(int index) {
        throw Unimplemented.forMember("net/minecraft/world/level/lighting/ChunkSkyLightSources.get:(I)I");
    }

    protected ChunkSkyLightSources() {
    }
}
