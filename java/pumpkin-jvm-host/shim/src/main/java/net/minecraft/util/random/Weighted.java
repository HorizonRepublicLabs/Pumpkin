package net.minecraft.util.random;

import java.util.function.Function;
import dev.pumpkin.shim.Unimplemented;

public record Weighted<T>(T value, int weight) {

    public <U> Weighted<U> map(Function<T, U> function) {
        throw Unimplemented.forMember("net/minecraft/util/random/Weighted.map:(Ljava/util/function/Function;)Lnet/minecraft/util/random/Weighted;");
    }
}
