package net.minecraft.world.level.levelgen;

import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public class XoroshiroRandomSource implements RandomSource {

    public XoroshiroRandomSource(long seed) {
    }

    public XoroshiroRandomSource(RandomSupport.Seed128bit seed) {
    }

    public XoroshiroRandomSource(long seedLo, long seedHi) {
    }

    private XoroshiroRandomSource(Xoroshiro128PlusPlus randomNumberGenerator) {
    }

    public RandomSource fork() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource.fork:()Lnet/minecraft/util/RandomSource;");
    }

    public PositionalRandomFactory forkPositional() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource.forkPositional:()Lnet/minecraft/world/level/levelgen/PositionalRandomFactory;");
    }

    public void setSeed(long seed) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource.setSeed:(J)V");
    }

    public int nextInt() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource.nextInt:()I");
    }

    public int nextInt(int bound) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource.nextInt:(I)I");
    }

    public long nextLong() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource.nextLong:()J");
    }

    public boolean nextBoolean() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource.nextBoolean:()Z");
    }

    public float nextFloat() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource.nextFloat:()F");
    }

    public double nextDouble() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource.nextDouble:()D");
    }

    public double nextGaussian() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource.nextGaussian:()D");
    }

    public void consumeCount(int rounds) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource.consumeCount:(I)V");
    }

    public static class XoroshiroPositionalRandomFactory implements PositionalRandomFactory {

        public XoroshiroPositionalRandomFactory(long seedLo, long seedHi) {
        }

        public RandomSource at(int x, int y, int z) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource$XoroshiroPositionalRandomFactory.at:(III)Lnet/minecraft/util/RandomSource;");
        }

        public RandomSource fromHashOf(String name) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource$XoroshiroPositionalRandomFactory.fromHashOf:(Ljava/lang/String;)Lnet/minecraft/util/RandomSource;");
        }

        public RandomSource fromSeed(long seed) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource$XoroshiroPositionalRandomFactory.fromSeed:(J)Lnet/minecraft/util/RandomSource;");
        }

        public void parityConfigString(StringBuilder sb) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/XoroshiroRandomSource$XoroshiroPositionalRandomFactory.parityConfigString:(Ljava/lang/StringBuilder;)V");
        }

        public XoroshiroPositionalRandomFactory() {
        }
    }

    public XoroshiroRandomSource() {
    }
}
