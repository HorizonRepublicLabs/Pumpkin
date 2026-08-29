package net.minecraft.world.level.levelgen;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.util.BoundedFloatFunction;
import net.minecraft.util.CubicSpline;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public final class DensityFunctions {

    protected DensityFunctions() {
    }

    public static DensityFunction add(DensityFunction f1, DensityFunction f2) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions.add:(Lnet/minecraft/world/level/levelgen/DensityFunction;Lnet/minecraft/world/level/levelgen/DensityFunction;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
    }

    private record Ap2(DensityFunctions.TwoArgumentSimpleFunction.Type type, DensityFunction argument1, DensityFunction argument2, double minValue, double maxValue) implements DensityFunctions.TwoArgumentSimpleFunction {

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Ap2.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Ap2.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Ap2.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }
    }

    enum BeardifierMarker implements DensityFunctions.BeardifierOrMarker {

        INSTANCE;

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BeardifierMarker.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BeardifierMarker.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BeardifierMarker.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BeardifierMarker.maxValue:()D");
        }
    }

    public interface BeardifierOrMarker extends DensityFunction.SimpleFunction {

        default KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BeardifierOrMarker.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }

    enum BlendAlpha implements DensityFunction.SimpleFunction {

        INSTANCE;

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BlendAlpha.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BlendAlpha.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BlendAlpha.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BlendAlpha.maxValue:()D");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BlendAlpha.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }

    enum BlendOffset implements DensityFunction.SimpleFunction {

        INSTANCE;

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BlendOffset.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BlendOffset.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BlendOffset.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BlendOffset.maxValue:()D");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$BlendOffset.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }

    protected record Clamp(DensityFunction input, double minValue, double maxValue) implements DensityFunctions.PureTransformer {

        public double transform(double input) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Clamp.transform:(D)D");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Clamp.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Clamp.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }

    private record Constant(double value) implements DensityFunction.SimpleFunction {

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Constant.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Constant.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Constant.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Constant.maxValue:()D");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Constant.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }

    protected static final class EndIslandDensityFunction implements DensityFunction.SimpleFunction {

        public EndIslandDensityFunction(long seed) {
        }

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$EndIslandDensityFunction.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$EndIslandDensityFunction.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$EndIslandDensityFunction.maxValue:()D");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$EndIslandDensityFunction.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }

        protected EndIslandDensityFunction() {
        }
    }

    private record FindTopSurface(DensityFunction density, DensityFunction upperBound, int lowerBound, int cellHeight) implements DensityFunction {

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$FindTopSurface.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$FindTopSurface.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$FindTopSurface.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$FindTopSurface.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$FindTopSurface.maxValue:()D");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$FindTopSurface.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }

    public record HolderHolder(Holder<DensityFunction> function) implements DensityFunction {

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$HolderHolder.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$HolderHolder.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$HolderHolder.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$HolderHolder.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$HolderHolder.maxValue:()D");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$HolderHolder.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }

    private record IntervalSelect(DensityFunction input, DoubleList thresholds, List<DensityFunction> functions) implements DensityFunction {

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$IntervalSelect.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$IntervalSelect.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$IntervalSelect.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$IntervalSelect.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$IntervalSelect.maxValue:()D");
        }

        public KeyDispatchDataCodec<DensityFunctions.IntervalSelect> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$IntervalSelect.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }

    protected record Mapped(DensityFunctions.Mapped.Type type, DensityFunction input, double minValue, double maxValue) implements DensityFunctions.PureTransformer {

        public static DensityFunctions.Mapped create(DensityFunctions.Mapped.Type type, DensityFunction input) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Mapped.create:(Lnet/minecraft/world/level/levelgen/DensityFunctions$Mapped$Type;Lnet/minecraft/world/level/levelgen/DensityFunction;)Lnet/minecraft/world/level/levelgen/DensityFunctions$Mapped;");
        }

        private static double transform(DensityFunctions.Mapped.Type type, double input) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Mapped.transform:(Lnet/minecraft/world/level/levelgen/DensityFunctions$Mapped$Type;D)D");
        }

        public double transform(double input) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Mapped.transform:(D)D");
        }

        public DensityFunctions.Mapped mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Mapped.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunctions$Mapped;");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Mapped.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }

        public enum Type implements StringRepresentable {

            ABS,
            SQUARE,
            CUBE,
            HALF_NEGATIVE,
            QUARTER_NEGATIVE,
            INVERT,
            SQUEEZE;

            public String getSerializedName() {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Mapped$Type.getSerializedName:()Ljava/lang/String;");
            }
        }
    }

    record Marker(DensityFunctions.Marker.Type type, DensityFunction wrapped) implements DensityFunctions.MarkerOrMarked {

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Marker.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Marker.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Marker.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Marker.maxValue:()D");
        }

        public enum Type implements StringRepresentable {

            Interpolated,
            FlatCache,
            Cache2D,
            CacheOnce,
            CacheAllInCell,
            BlendDensity;

            public String getSerializedName() {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Marker$Type.getSerializedName:()Ljava/lang/String;");
            }
        }
    }

    public interface MarkerOrMarked extends DensityFunction {

        DensityFunctions.Marker.Type type();

        DensityFunction wrapped();

        default KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$MarkerOrMarked.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }

        default DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$MarkerOrMarked.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }
    }

    private record MulOrAdd(DensityFunctions.MulOrAdd.Type specificType, DensityFunction input, double minValue, double maxValue, double argument) implements DensityFunctions.TwoArgumentSimpleFunction, DensityFunctions.PureTransformer {

        public DensityFunctions.TwoArgumentSimpleFunction.Type type() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$MulOrAdd.type:()Lnet/minecraft/world/level/levelgen/DensityFunctions$TwoArgumentSimpleFunction$Type;");
        }

        public DensityFunction argument1() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$MulOrAdd.argument1:()Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public DensityFunction argument2() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$MulOrAdd.argument2:()Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public double transform(double input) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$MulOrAdd.transform:(D)D");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$MulOrAdd.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public enum Type {

            MUL, ADD
        }
    }

    protected record Noise(DensityFunction.NoiseHolder noise, double xzScale, double yScale) implements DensityFunction {

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Noise.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Noise.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Noise.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Noise.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Noise.maxValue:()D");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Noise.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }

    private interface PureTransformer extends DensityFunction {

        DensityFunction input();

        default double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$PureTransformer.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        default void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$PureTransformer.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        double transform(final double input);
    }

    private record RangeChoice(DensityFunction input, double minInclusive, double maxExclusive, DensityFunction whenInRange, DensityFunction whenOutOfRange) implements DensityFunction {

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$RangeChoice.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$RangeChoice.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$RangeChoice.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$RangeChoice.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$RangeChoice.maxValue:()D");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$RangeChoice.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }

    protected record Shift(DensityFunction.NoiseHolder offsetNoise) implements DensityFunctions.ShiftNoise {

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Shift.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Shift.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Shift.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }

    protected record ShiftA(DensityFunction.NoiseHolder offsetNoise) implements DensityFunctions.ShiftNoise {

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftA.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftA.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftA.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }

    protected record ShiftB(DensityFunction.NoiseHolder offsetNoise) implements DensityFunctions.ShiftNoise {

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftB.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftB.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftB.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }

    protected interface ShiftNoise extends DensityFunction {

        DensityFunction.NoiseHolder offsetNoise();

        default double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftNoise.minValue:()D");
        }

        default double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftNoise.maxValue:()D");
        }

        default void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftNoise.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }
    }

    protected record ShiftedNoise(DensityFunction shiftX, DensityFunction shiftY, DensityFunction shiftZ, double xzScale, double yScale, DensityFunction.NoiseHolder noise) implements DensityFunction {

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftedNoise.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftedNoise.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftedNoise.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftedNoise.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftedNoise.maxValue:()D");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$ShiftedNoise.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }

    public static final class Spline implements DensityFunction {

        public Spline(CubicSpline<DensityFunctions.Spline.Coordinate> spline) {
        }

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Spline.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Spline.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Spline.maxValue:()D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Spline.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Spline.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Spline.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }

        public boolean equals(Object obj) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Spline.equals:(Ljava/lang/Object;)Z");
        }

        public int hashCode() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Spline.hashCode:()I");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Spline.toString:()Ljava/lang/String;");
        }

        public record Coordinate(DensityFunction function) implements BoundedFloatFunction<DensityFunctions.Spline.Point> {

            public float apply(DensityFunctions.Spline.Point point) {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Spline$Coordinate.apply:(Lnet/minecraft/world/level/levelgen/DensityFunctions$Spline$Point;)F");
            }

            public float minValue() {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Spline$Coordinate.minValue:()F");
            }

            public float maxValue() {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Spline$Coordinate.maxValue:()F");
            }

            public DensityFunctions.Spline.Coordinate mapChildren(DensityFunction.Visitor visitor) {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$Spline$Coordinate.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunctions$Spline$Coordinate;");
            }
        }

        public record Point(DensityFunction.FunctionContext context) {
        }

        public Spline() {
        }
    }

    private interface TransformerWithContext extends DensityFunction {

        DensityFunction input();

        default double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$TransformerWithContext.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        default void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$TransformerWithContext.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        double transform(DensityFunction.FunctionContext contextSupplier, final double input);
    }

    public interface TwoArgumentSimpleFunction extends DensityFunction {

        static DensityFunctions.TwoArgumentSimpleFunction create(DensityFunctions.TwoArgumentSimpleFunction.Type type, DensityFunction argument1, DensityFunction argument2) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$TwoArgumentSimpleFunction.create:(Lnet/minecraft/world/level/levelgen/DensityFunctions$TwoArgumentSimpleFunction$Type;Lnet/minecraft/world/level/levelgen/DensityFunction;Lnet/minecraft/world/level/levelgen/DensityFunction;)Lnet/minecraft/world/level/levelgen/DensityFunctions$TwoArgumentSimpleFunction;");
        }

        DensityFunctions.TwoArgumentSimpleFunction.Type type();

        DensityFunction argument1();

        DensityFunction argument2();

        default KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$TwoArgumentSimpleFunction.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }

        enum Type implements StringRepresentable {

            ADD, MUL, MIN, MAX;

            public String getSerializedName() {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$TwoArgumentSimpleFunction$Type.getSerializedName:()Ljava/lang/String;");
            }
        }
    }

    private record YClampedGradient(int fromY, int toY, double fromValue, double toValue) implements DensityFunction.SimpleFunction {

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$YClampedGradient.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$YClampedGradient.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$YClampedGradient.maxValue:()D");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunctions$YClampedGradient.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }
    }
}
