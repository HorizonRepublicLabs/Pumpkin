package net.minecraft.world.level.levelgen;

import net.minecraft.core.Holder;
import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import dev.pumpkin.shim.Unimplemented;

public interface DensityFunction {

    double compute(final DensityFunction.FunctionContext context);

    void fillArray(final double[] output, final DensityFunction.ContextProvider contextProvider);

    DensityFunction mapChildren(final DensityFunction.Visitor visitor);

    double minValue();

    double maxValue();

    KeyDispatchDataCodec<? extends DensityFunction> codec();

    interface ContextProvider {

        DensityFunction.FunctionContext forIndex(int index);

        void fillAllDirectly(double[] output, DensityFunction function);
    }

    interface FunctionContext {

        int blockX();

        int blockY();

        int blockZ();
    }

    record NoiseHolder(Holder<NormalNoise.NoiseParameters> noiseData, NormalNoise noise) {

        public NoiseHolder(Holder<NormalNoise.NoiseParameters> noiseData) {
            this((Holder<NormalNoise.NoiseParameters>) null, (NormalNoise) null);
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunction$NoiseHolder.<init>:(Lnet/minecraft/core/Holder;)V");
        }

        public double getValue(double x, double y, double z) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunction$NoiseHolder.getValue:(DDD)D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunction$NoiseHolder.maxValue:()D");
        }
    }

    interface SimpleFunction extends DensityFunction {

        default void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunction$SimpleFunction.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        default DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/DensityFunction$SimpleFunction.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }
    }

    record SinglePointContext(int blockX, int blockY, int blockZ) implements DensityFunction.FunctionContext {
    }

    interface Visitor {

        DensityFunction apply(DensityFunction input);
    }
}
