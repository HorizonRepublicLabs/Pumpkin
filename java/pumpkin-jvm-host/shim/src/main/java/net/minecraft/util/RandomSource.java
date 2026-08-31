package net.minecraft.util;

import net.minecraft.world.level.levelgen.PositionalRandomFactory;
import dev.pumpkin.shim.Unimplemented;

public interface RandomSource {

    static RandomSource create() {
        throw Unimplemented.forMember("net/minecraft/util/RandomSource.create:()Lnet/minecraft/util/RandomSource;");
    }

    static RandomSource createThreadSafe() {
        throw Unimplemented.forMember("net/minecraft/util/RandomSource.createThreadSafe:()Lnet/minecraft/util/RandomSource;");
    }

    static RandomSource create(long seed) {
        throw Unimplemented.forMember("net/minecraft/util/RandomSource.create:(J)Lnet/minecraft/util/RandomSource;");
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
