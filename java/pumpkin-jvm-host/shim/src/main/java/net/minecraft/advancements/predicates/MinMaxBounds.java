package net.minecraft.advancements.predicates;

import java.util.Optional;
import java.util.function.Function;
import dev.pumpkin.shim.Unimplemented;

public interface MinMaxBounds<T extends Number & Comparable<T>> {

    MinMaxBounds.Bounds<T> bounds();

    default Optional<T> min() {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/MinMaxBounds.min:()Ljava/util/Optional;");
    }

    default Optional<T> max() {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/MinMaxBounds.max:()Ljava/util/Optional;");
    }

    record Bounds<T extends Number & Comparable<T>>(Optional<T> min, Optional<T> max) {

        public <U extends Number & Comparable<U>> MinMaxBounds.Bounds<U> map(Function<T, U> mapper) {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/MinMaxBounds$Bounds.map:(Ljava/util/function/Function;)Lnet/minecraft/advancements/predicates/MinMaxBounds$Bounds;");
        }
    }

    record Doubles(MinMaxBounds.Bounds<Double> bounds, MinMaxBounds.Bounds<Double> boundsSqr) implements MinMaxBounds<Double> {

        private Doubles(MinMaxBounds.Bounds<Double> bounds) {
            this((MinMaxBounds.Bounds<Double>) null, (MinMaxBounds.Bounds<Double>) null);
        }

        public boolean matches(double value) {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/MinMaxBounds$Doubles.matches:(D)Z");
        }
    }

    record FloatDegrees(MinMaxBounds.Bounds<Float> bounds) implements MinMaxBounds<Float> {
    }

    record Ints(MinMaxBounds.Bounds<Integer> bounds, MinMaxBounds.Bounds<Long> boundsSqr) implements MinMaxBounds<Integer> {

        private Ints(MinMaxBounds.Bounds<Integer> bounds) {
            this((MinMaxBounds.Bounds<Integer>) null, (MinMaxBounds.Bounds<Long>) null);
        }

        public boolean matches(int value) {
            throw Unimplemented.forMember("net/minecraft/advancements/predicates/MinMaxBounds$Ints.matches:(I)Z");
        }
    }
}
