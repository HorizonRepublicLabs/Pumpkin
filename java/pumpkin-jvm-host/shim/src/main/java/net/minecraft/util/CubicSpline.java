package net.minecraft.util;

import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import java.util.List;
import java.util.function.UnaryOperator;
import dev.pumpkin.shim.Unimplemented;

public interface CubicSpline<I> {

    CubicSpline<I> mapCoordinates(UnaryOperator<I> mapper);

    float minValue();

    float maxValue();

    String parityString();

    static <C, I extends BoundedFloatFunction<C>> float sample(CubicSpline<I> spline, C coordinate) {
        throw Unimplemented.forMember("net/minecraft/util/CubicSpline.sample:(Lnet/minecraft/util/CubicSpline;Ljava/lang/Object;)F");
    }

    final class Builder<I extends BoundedFloatFunction<?>> {

        private Builder(I coordinate) {
            throw Unimplemented.forMember("net/minecraft/util/CubicSpline$Builder.<init>:(Lnet/minecraft/util/BoundedFloatFunction;)V");
        }

        private Builder(I coordinate, Float2FloatFunction valueTransformer) {
            throw Unimplemented.forMember("net/minecraft/util/CubicSpline$Builder.<init>:(Lnet/minecraft/util/BoundedFloatFunction;Lit/unimi/dsi/fastutil/floats/Float2FloatFunction;)V");
        }

        public CubicSpline.Builder<I> addPoint(float location, float value, float derivative) {
            throw Unimplemented.forMember("net/minecraft/util/CubicSpline$Builder.addPoint:(FFF)Lnet/minecraft/util/CubicSpline$Builder;");
        }

        private CubicSpline.Builder<I> addPoint(float location, CubicSpline<I> sampler, float derivative) {
            throw Unimplemented.forMember("net/minecraft/util/CubicSpline$Builder.addPoint:(FLnet/minecraft/util/CubicSpline;F)Lnet/minecraft/util/CubicSpline$Builder;");
        }

        public CubicSpline<I> build() {
            throw Unimplemented.forMember("net/minecraft/util/CubicSpline$Builder.build:()Lnet/minecraft/util/CubicSpline;");
        }

        protected Builder() {
        }
    }

    record Constant<I>(float value) implements CubicSpline<I> {

        public String parityString() {
            throw Unimplemented.forMember("net/minecraft/util/CubicSpline$Constant.parityString:()Ljava/lang/String;");
        }

        public float minValue() {
            throw Unimplemented.forMember("net/minecraft/util/CubicSpline$Constant.minValue:()F");
        }

        public float maxValue() {
            throw Unimplemented.forMember("net/minecraft/util/CubicSpline$Constant.maxValue:()F");
        }

        public CubicSpline<I> mapCoordinates(UnaryOperator<I> mapper) {
            throw Unimplemented.forMember("net/minecraft/util/CubicSpline$Constant.mapCoordinates:(Ljava/util/function/UnaryOperator;)Lnet/minecraft/util/CubicSpline;");
        }
    }

    record Multipoint<I extends BoundedFloatFunction<?>>(I coordinate, float[] locations, List<CubicSpline<I>> values, float[] derivatives, float minValue, float maxValue) implements CubicSpline<I> {

        public Multipoint(I coordinate, float[] locations, List<CubicSpline<I>> values, float[] derivatives) {
            this((I) null, (float[]) null, (List<CubicSpline<I>>) null, (float[]) null, (float) 0.0F, (float) 0.0F);
            throw Unimplemented.forMember("net/minecraft/util/CubicSpline$Multipoint.<init>:(Lnet/minecraft/util/BoundedFloatFunction;[FLjava/util/List;[F)V");
        }

        public static <C, I extends BoundedFloatFunction<C>> float sample(CubicSpline.Multipoint<I> sampler, C c) {
            throw Unimplemented.forMember("net/minecraft/util/CubicSpline$Multipoint.sample:(Lnet/minecraft/util/CubicSpline$Multipoint;Ljava/lang/Object;)F");
        }

        public String parityString() {
            throw Unimplemented.forMember("net/minecraft/util/CubicSpline$Multipoint.parityString:()Ljava/lang/String;");
        }

        public CubicSpline<I> mapCoordinates(UnaryOperator<I> mapper) {
            throw Unimplemented.forMember("net/minecraft/util/CubicSpline$Multipoint.mapCoordinates:(Ljava/util/function/UnaryOperator;)Lnet/minecraft/util/CubicSpline;");
        }

        private record Point<I extends BoundedFloatFunction<?>>(float location, CubicSpline<I> value, float derivative) {
        }
    }
}
