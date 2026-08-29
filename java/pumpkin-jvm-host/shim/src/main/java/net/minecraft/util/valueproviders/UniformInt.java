package net.minecraft.util.valueproviders;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public record UniformInt(int minInclusive, int maxInclusive) implements IntProvider {

    // Pumpkin divergence: real body, copied from vanilla -- a record over two ints and
    // its own constructor. The ARGB rule.
    public static UniformInt of(int minInclusive, int maxInclusive) {
        return new UniformInt(minInclusive, maxInclusive);
    }

    public int sample(RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/util/valueproviders/UniformInt.sample:(Lnet/minecraft/util/RandomSource;)I");
    }

    public MapCodec<UniformInt> codec() {
        throw Unimplemented.forMember("net/minecraft/util/valueproviders/UniformInt.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/util/valueproviders/UniformInt.toString:()Ljava/lang/String;");
    }
}
