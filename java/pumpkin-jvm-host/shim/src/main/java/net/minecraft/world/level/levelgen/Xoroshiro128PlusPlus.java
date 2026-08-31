package net.minecraft.world.level.levelgen;

import dev.pumpkin.shim.Unimplemented;

public class Xoroshiro128PlusPlus {

    public Xoroshiro128PlusPlus(RandomSupport.Seed128bit seed) {
    }

    public Xoroshiro128PlusPlus(long seedLo, long seedHi) {
    }

    public long nextLong() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/Xoroshiro128PlusPlus.nextLong:()J");
    }

    public Xoroshiro128PlusPlus() {
    }
}
