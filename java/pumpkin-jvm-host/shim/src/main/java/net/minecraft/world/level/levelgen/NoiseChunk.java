package net.minecraft.world.level.levelgen;

import net.minecraft.util.KeyDispatchDataCodec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blending.Blender;
import dev.pumpkin.shim.Unimplemented;

public class NoiseChunk implements DensityFunction.FunctionContext, DensityFunction.ContextProvider {

    public NoiseChunk(int cellCountXZ, RandomState randomState, int chunkMinBlockX, int chunkMinBlockZ, NoiseSettings noiseSettings, DensityFunctions.BeardifierOrMarker beardifier, NoiseGeneratorSettings settings, Aquifer.FluidPicker globalFluidPicker, Blender blender) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk.<init>:(ILnet/minecraft/world/level/levelgen/RandomState;IILnet/minecraft/world/level/levelgen/NoiseSettings;Lnet/minecraft/world/level/levelgen/DensityFunctions$BeardifierOrMarker;Lnet/minecraft/world/level/levelgen/NoiseGeneratorSettings;Lnet/minecraft/world/level/levelgen/Aquifer$FluidPicker;Lnet/minecraft/world/level/levelgen/blending/Blender;)V");
    }

    public int blockX() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk.blockX:()I");
    }

    public int blockY() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk.blockY:()I");
    }

    public int blockZ() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk.blockZ:()I");
    }

    public NoiseChunk forIndex(int cellIndex) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk.forIndex:(I)Lnet/minecraft/world/level/levelgen/NoiseChunk;");
    }

    public void fillAllDirectly(double[] output, DensityFunction function) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk.fillAllDirectly:([DLnet/minecraft/world/level/levelgen/DensityFunction;)V");
    }

    private class BlendAlpha implements NoiseChunk.NoiseChunkDensityFunction {

        public DensityFunction wrapped() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendAlpha.wrapped:()Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendAlpha.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendAlpha.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendAlpha.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendAlpha.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendAlpha.maxValue:()D");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendAlpha.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }

        protected BlendAlpha() {
        }
    }

    private class BlendDensity implements NoiseChunk.NoiseChunkDensityFunction, DensityFunctions.MarkerOrMarked {

        private BlendDensity(DensityFunction input) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendDensity.<init>:(Lnet/minecraft/world/level/levelgen/DensityFunction;)V");
        }

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendDensity.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendDensity.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendDensity.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendDensity.maxValue:()D");
        }

        public DensityFunction wrapped() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendDensity.wrapped:()Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public DensityFunctions.Marker.Type type() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendDensity.type:()Lnet/minecraft/world/level/levelgen/DensityFunctions$Marker$Type;");
        }

        protected BlendDensity() {
        }
    }

    private class BlendOffset implements NoiseChunk.NoiseChunkDensityFunction {

        public DensityFunction wrapped() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendOffset.wrapped:()Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public DensityFunction mapChildren(DensityFunction.Visitor visitor) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendOffset.mapChildren:(Lnet/minecraft/world/level/levelgen/DensityFunction$Visitor;)Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendOffset.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendOffset.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendOffset.minValue:()D");
        }

        public double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendOffset.maxValue:()D");
        }

        public KeyDispatchDataCodec<? extends DensityFunction> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$BlendOffset.codec:()Lnet/minecraft/util/KeyDispatchDataCodec;");
        }

        protected BlendOffset() {
        }
    }

    public interface BlockStateFiller {

        BlockState calculate(final DensityFunction.FunctionContext context);
    }

    private static class Cache2D implements NoiseChunk.NoiseChunkDensityFunction, DensityFunctions.MarkerOrMarked {

        private Cache2D(DensityFunction function) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$Cache2D.<init>:(Lnet/minecraft/world/level/levelgen/DensityFunction;)V");
        }

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$Cache2D.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$Cache2D.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public DensityFunction wrapped() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$Cache2D.wrapped:()Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public DensityFunctions.Marker.Type type() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$Cache2D.type:()Lnet/minecraft/world/level/levelgen/DensityFunctions$Marker$Type;");
        }

        protected Cache2D() {
        }
    }

    private class CacheAllInCell implements NoiseChunk.NoiseChunkDensityFunction, DensityFunctions.MarkerOrMarked {

        private CacheAllInCell(DensityFunction noiseFiller) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$CacheAllInCell.<init>:(Lnet/minecraft/world/level/levelgen/DensityFunction;)V");
        }

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$CacheAllInCell.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$CacheAllInCell.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public DensityFunction wrapped() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$CacheAllInCell.wrapped:()Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public DensityFunctions.Marker.Type type() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$CacheAllInCell.type:()Lnet/minecraft/world/level/levelgen/DensityFunctions$Marker$Type;");
        }

        protected CacheAllInCell() {
        }
    }

    private class CacheOnce implements NoiseChunk.NoiseChunkDensityFunction, DensityFunctions.MarkerOrMarked {

        private CacheOnce(DensityFunction function) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$CacheOnce.<init>:(Lnet/minecraft/world/level/levelgen/DensityFunction;)V");
        }

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$CacheOnce.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$CacheOnce.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public DensityFunction wrapped() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$CacheOnce.wrapped:()Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public DensityFunctions.Marker.Type type() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$CacheOnce.type:()Lnet/minecraft/world/level/levelgen/DensityFunctions$Marker$Type;");
        }

        protected CacheOnce() {
        }
    }

    private class FlatCache implements NoiseChunk.NoiseChunkDensityFunction, DensityFunctions.MarkerOrMarked {

        private FlatCache(DensityFunction noiseFiller, boolean fill) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$FlatCache.<init>:(Lnet/minecraft/world/level/levelgen/DensityFunction;Z)V");
        }

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$FlatCache.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$FlatCache.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public DensityFunction wrapped() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$FlatCache.wrapped:()Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public DensityFunctions.Marker.Type type() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$FlatCache.type:()Lnet/minecraft/world/level/levelgen/DensityFunctions$Marker$Type;");
        }

        protected FlatCache() {
        }
    }

    private interface NoiseChunkDensityFunction extends DensityFunction {

        DensityFunction wrapped();

        default double minValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$NoiseChunkDensityFunction.minValue:()D");
        }

        default double maxValue() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$NoiseChunkDensityFunction.maxValue:()D");
        }
    }

    public class NoiseInterpolator implements NoiseChunk.NoiseChunkDensityFunction, DensityFunctions.MarkerOrMarked {

        private NoiseInterpolator(DensityFunction noiseFiller) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$NoiseInterpolator.<init>:(Lnet/minecraft/world/level/levelgen/DensityFunction;)V");
        }

        public double compute(DensityFunction.FunctionContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$NoiseInterpolator.compute:(Lnet/minecraft/world/level/levelgen/DensityFunction$FunctionContext;)D");
        }

        public void fillArray(double[] output, DensityFunction.ContextProvider contextProvider) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$NoiseInterpolator.fillArray:([DLnet/minecraft/world/level/levelgen/DensityFunction$ContextProvider;)V");
        }

        public DensityFunction wrapped() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$NoiseInterpolator.wrapped:()Lnet/minecraft/world/level/levelgen/DensityFunction;");
        }

        public DensityFunctions.Marker.Type type() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/NoiseChunk$NoiseInterpolator.type:()Lnet/minecraft/world/level/levelgen/DensityFunctions$Marker$Type;");
        }

        protected NoiseInterpolator() {
        }
    }

    protected NoiseChunk() {
    }
}
