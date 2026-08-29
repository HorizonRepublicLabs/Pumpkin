package net.minecraft.world.level.chunk;

import dev.pumpkin.shim.Unimplemented;

public class CarvingMask {

    public CarvingMask(int height, int minY) {
    }

    public CarvingMask(long[] array, int minY) {
    }

    public void set(int x, int y, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/CarvingMask.set:(III)V");
    }

    public boolean get(int x, int y, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/CarvingMask.get:(III)Z");
    }

    public interface Mask {

        boolean test(int x, int y, int z);
    }

    public CarvingMask() {
    }
}
