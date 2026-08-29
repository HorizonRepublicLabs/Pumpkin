package net.minecraft.world.level.levelgen;

import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public class LegacyRandomSource implements BitRandomSource {

    public LegacyRandomSource(long seed) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/LegacyRandomSource.<init>:(J)V");
    }

    public RandomSource fork() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/LegacyRandomSource.fork:()Lnet/minecraft/util/RandomSource;");
    }

    public PositionalRandomFactory forkPositional() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/LegacyRandomSource.forkPositional:()Lnet/minecraft/world/level/levelgen/PositionalRandomFactory;");
    }

    public void setSeed(long seed) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/LegacyRandomSource.setSeed:(J)V");
    }

    public int next(int bits) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/LegacyRandomSource.next:(I)I");
    }

    public double nextGaussian() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/LegacyRandomSource.nextGaussian:()D");
    }

    public static class LegacyPositionalRandomFactory implements PositionalRandomFactory {

        public LegacyPositionalRandomFactory(long seed) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/LegacyRandomSource$LegacyPositionalRandomFactory.<init>:(J)V");
        }

        public RandomSource at(int x, int y, int z) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/LegacyRandomSource$LegacyPositionalRandomFactory.at:(III)Lnet/minecraft/util/RandomSource;");
        }

        public RandomSource fromHashOf(String name) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/LegacyRandomSource$LegacyPositionalRandomFactory.fromHashOf:(Ljava/lang/String;)Lnet/minecraft/util/RandomSource;");
        }

        public RandomSource fromSeed(long seed) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/LegacyRandomSource$LegacyPositionalRandomFactory.fromSeed:(J)Lnet/minecraft/util/RandomSource;");
        }

        public void parityConfigString(StringBuilder sb) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/LegacyRandomSource$LegacyPositionalRandomFactory.parityConfigString:(Ljava/lang/StringBuilder;)V");
        }

        public LegacyPositionalRandomFactory() {
        }
    }

    public LegacyRandomSource() {
    }
}
