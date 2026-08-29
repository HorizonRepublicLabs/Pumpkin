package net.minecraft.world.level.chunk;

import dev.pumpkin.shim.Unimplemented;

public class DataLayer {

    public DataLayer() {
    }

    public DataLayer(int defaultValue) {
    }

    public DataLayer(byte[] data) {
    }

    public int get(int x, int y, int z) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/DataLayer.get:(III)I");
    }

    private int get(int index) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/DataLayer.get:(I)I");
    }

    private void set(int index, int val) {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/DataLayer.set:(II)V");
    }

    public DataLayer copy() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/DataLayer.copy:()Lnet/minecraft/world/level/chunk/DataLayer;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/DataLayer.toString:()Ljava/lang/String;");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/level/chunk/DataLayer.isEmpty:()Z");
    }
}
