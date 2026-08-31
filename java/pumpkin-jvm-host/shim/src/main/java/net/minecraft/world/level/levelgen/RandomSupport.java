package net.minecraft.world.level.levelgen;

import dev.pumpkin.shim.Unimplemented;

public final class RandomSupport {

    public static long generateUniqueSeed() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/RandomSupport.generateUniqueSeed:()J");
    }

    public record Seed128bit(long seedLo, long seedHi) {
    }

    public RandomSupport() {
    }
}
