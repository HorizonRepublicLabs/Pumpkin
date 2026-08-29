package net.minecraft.world.level.levelgen;

import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public interface BitRandomSource extends RandomSource {

    int next(final int bits);

    default int nextInt() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/BitRandomSource.nextInt:()I");
    }

    default int nextInt(int bound) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/BitRandomSource.nextInt:(I)I");
    }

    default long nextLong() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/BitRandomSource.nextLong:()J");
    }

    default boolean nextBoolean() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/BitRandomSource.nextBoolean:()Z");
    }

    default float nextFloat() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/BitRandomSource.nextFloat:()F");
    }

    default double nextDouble() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/BitRandomSource.nextDouble:()D");
    }
}
