package net.minecraft.world.level.levelgen.synth;

import it.unimi.dsi.fastutil.doubles.DoubleList;
import java.util.List;
import net.minecraft.util.RandomSource;
import dev.pumpkin.shim.Unimplemented;

public class NormalNoise {

    public static NormalNoise create(RandomSource random, int firstOctave, double... amplitudes) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/synth/NormalNoise.create:(Lnet/minecraft/util/RandomSource;I[D)Lnet/minecraft/world/level/levelgen/synth/NormalNoise;");
    }

    public static NormalNoise create(RandomSource random, NormalNoise.NoiseParameters parameters) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/synth/NormalNoise.create:(Lnet/minecraft/util/RandomSource;Lnet/minecraft/world/level/levelgen/synth/NormalNoise$NoiseParameters;)Lnet/minecraft/world/level/levelgen/synth/NormalNoise;");
    }

    private NormalNoise(RandomSource random, NormalNoise.NoiseParameters parameters, boolean useNewInitialization) {
    }

    public double maxValue() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/synth/NormalNoise.maxValue:()D");
    }

    public double getValue(double x, double y, double z) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/synth/NormalNoise.getValue:(DDD)D");
    }

    public void parityConfigString(StringBuilder sb) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/synth/NormalNoise.parityConfigString:(Ljava/lang/StringBuilder;)V");
    }

    public record NoiseParameters(int firstOctave, DoubleList amplitudes) {

        public NoiseParameters(int firstOctave, List<Double> amplitudes) {
            this((int) 0, (DoubleList) null);
        }

        public NoiseParameters(int firstOctave, double firstAmplitude, double... amplitudes) {
            this((int) 0, (DoubleList) null);
        }
    }

    public NormalNoise() {
    }
}
