package net.minecraft.world.level.levelgen;

import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public class WorldgenRandom extends LegacyRandomSource {

    public WorldgenRandom(RandomSource randomSource) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldgenRandom.<init>:(Lnet/minecraft/util/RandomSource;)V");
    }

    public int getCount() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldgenRandom.getCount:()I");
    }

    public RandomSource fork() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldgenRandom.fork:()Lnet/minecraft/util/RandomSource;");
    }

    public PositionalRandomFactory forkPositional() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldgenRandom.forkPositional:()Lnet/minecraft/world/level/levelgen/PositionalRandomFactory;");
    }

    public int next(int bits) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldgenRandom.next:(I)I");
    }

    public synchronized void setSeed(long seed) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/WorldgenRandom.setSeed:(J)V");
    }

    public enum Algorithm {

        LEGACY, XOROSHIRO
    }

    protected WorldgenRandom() {
    }
}
