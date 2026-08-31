package net.minecraft.util;

import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import dev.pumpkin.shim.Unimplemented;

public interface RandomSource {

    // Pumpkin divergence: a real random over java.util.Random -- the same choice the
    // interaction bridge's level makes. Mods want noise, not stubs, from these.
    private static RandomSource pumpkinRandom(java.util.Random random) {
        return new RandomSource() {
            public RandomSource fork() {
                return pumpkinRandom(new java.util.Random(random.nextLong()));
            }

            public net.minecraft.world.level.levelgen.PositionalRandomFactory forkPositional() {
                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/util/RandomSource.forkPositional:()Lnet/minecraft/world/level/levelgen/PositionalRandomFactory;");
            }

            public void setSeed(long seed) {
                random.setSeed(seed);
            }

            public int nextInt() {
                return random.nextInt();
            }

            public int nextInt(int bound) {
                return random.nextInt(bound);
            }

            public long nextLong() {
                return random.nextLong();
            }

            public boolean nextBoolean() {
                return random.nextBoolean();
            }

            public float nextFloat() {
                return random.nextFloat();
            }

            public double nextDouble() {
                return random.nextDouble();
            }

            public double nextGaussian() {
                return random.nextGaussian();
            }
        };
    }


    static RandomSource create() {
        return pumpkinRandom(new java.util.Random());
    }

    static RandomSource createThreadSafe() {
        return pumpkinRandom(new java.util.Random());
    }

    static RandomSource create(long seed) {
        return pumpkinRandom(new java.util.Random(seed));
    }

    RandomSource fork();

    PositionalRandomFactory forkPositional();

    void setSeed(long seed);

    int nextInt();

    int nextInt(int bound);

    default int nextIntBetweenInclusive(int min, int maxInclusive) {
        throw Unimplemented.forMember("net/minecraft/util/RandomSource.nextIntBetweenInclusive:(II)I");
    }

    long nextLong();

    boolean nextBoolean();

    float nextFloat();

    double nextDouble();

    double nextGaussian();

    default double triangle(double mean, double spread) {
        throw Unimplemented.forMember("net/minecraft/util/RandomSource.triangle:(DD)D");
    }

    default float triangle(float mean, float spread) {
        throw Unimplemented.forMember("net/minecraft/util/RandomSource.triangle:(FF)F");
    }

    default int nextInt(int origin, int bound) {
        throw Unimplemented.forMember("net/minecraft/util/RandomSource.nextInt:(II)I");
    }
}
