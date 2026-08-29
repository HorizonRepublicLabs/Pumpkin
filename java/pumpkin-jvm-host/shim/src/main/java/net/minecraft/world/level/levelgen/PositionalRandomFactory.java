package net.minecraft.world.level.levelgen;

import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public interface PositionalRandomFactory {

    default RandomSource fromHashOf(Identifier name) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/PositionalRandomFactory.fromHashOf:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/util/RandomSource;");
    }

    RandomSource fromHashOf(final String name);

    RandomSource fromSeed(final long seed);

    RandomSource at(final int x, final int y, final int z);

    void parityConfigString(StringBuilder sb);
}
