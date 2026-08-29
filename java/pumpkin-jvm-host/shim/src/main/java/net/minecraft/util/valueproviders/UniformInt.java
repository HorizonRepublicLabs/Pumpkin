package net.minecraft.util.valueproviders;

import com.mojang.serialization.MapCodec;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public record UniformInt(int minInclusive, int maxInclusive) implements IntProvider {

    public static UniformInt of(int minInclusive, int maxInclusive) {
        throw Unimplemented.forMember("net/minecraft/util/valueproviders/UniformInt.of:(II)Lnet/minecraft/util/valueproviders/UniformInt;");
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
