package net.minecraft.world.level.levelgen;

import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.placement.CaveSurface;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import dev.pumpkin.shim.Unimplemented;

public class SurfaceRules {

    private enum AbovePreliminarySurface implements SurfaceRules.ConditionSource {

        INSTANCE;

        public MapCodec<SurfaceRules.AbovePreliminarySurface> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$AbovePreliminarySurface.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.Condition apply(SurfaceRules.Context context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$AbovePreliminarySurface.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$Condition;");
        }
    }

    private enum Bandlands implements SurfaceRules.RuleSource {

        INSTANCE;

        public MapCodec<SurfaceRules.Bandlands> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Bandlands.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.SurfaceRule apply(SurfaceRules.Context context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Bandlands.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$SurfaceRule;");
        }
    }

    private record BiomeConditionSource(HolderSet<Biome> biomes) implements SurfaceRules.ConditionSource {

        public MapCodec<SurfaceRules.BiomeConditionSource> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$BiomeConditionSource.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.Condition apply(SurfaceRules.Context ruleContext) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$BiomeConditionSource.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$Condition;");
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$BiomeConditionSource.toString:()Ljava/lang/String;");
        }
    }

    private record BlockRuleSource(BlockState resultState, SurfaceRules.StateRule rule) implements SurfaceRules.RuleSource {

        private BlockRuleSource(BlockState state) {
            this((BlockState) null, (SurfaceRules.StateRule) null);
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$BlockRuleSource.<init>:(Lnet/minecraft/world/level/block/state/BlockState;)V");
        }

        public MapCodec<SurfaceRules.BlockRuleSource> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$BlockRuleSource.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.SurfaceRule apply(SurfaceRules.Context context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$BlockRuleSource.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$SurfaceRule;");
        }
    }

    private interface Condition {

        boolean test();
    }

    public interface ConditionSource extends Function<SurfaceRules.Context, SurfaceRules.Condition> {

        MapCodec<? extends SurfaceRules.ConditionSource> codec();
    }

    protected static final class Context {

        protected Context(SurfaceSystem system, RandomState randomState, ChunkAccess chunk, NoiseChunk noiseChunk, Function<BlockPos, Holder<Biome>> biomeGetter, WorldGenerationContext context, Set<Holder<Biome>> possibleBiomes) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Context.<init>:(Lnet/minecraft/world/level/levelgen/SurfaceSystem;Lnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/world/level/levelgen/NoiseChunk;Ljava/util/function/Function;Lnet/minecraft/world/level/levelgen/WorldGenerationContext;Ljava/util/Set;)V");
        }

        public int getSeaLevel() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Context.getSeaLevel:()I");
        }

        private final class AbovePreliminarySurfaceCondition implements SurfaceRules.Condition {

            public boolean test() {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Context$AbovePreliminarySurfaceCondition.test:()Z");
            }

            protected AbovePreliminarySurfaceCondition() {
            }
        }

        private static final class HoleCondition extends SurfaceRules.LazyXZCondition {

            private HoleCondition(SurfaceRules.Context context) {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Context$HoleCondition.<init>:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)V");
            }

            protected boolean compute() {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Context$HoleCondition.compute:()Z");
            }

            protected HoleCondition() {
            }
        }

        private static class SteepMaterialCondition extends SurfaceRules.LazyXZCondition {

            private SteepMaterialCondition(SurfaceRules.Context context) {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Context$SteepMaterialCondition.<init>:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)V");
            }

            protected boolean compute() {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Context$SteepMaterialCondition.compute:()Z");
            }

            protected SteepMaterialCondition() {
            }
        }

        private static class TemperatureHelperCondition extends SurfaceRules.LazyYCondition {

            private TemperatureHelperCondition(SurfaceRules.Context context) {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Context$TemperatureHelperCondition.<init>:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)V");
            }

            protected boolean compute() {
                throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Context$TemperatureHelperCondition.compute:()Z");
            }

            protected TemperatureHelperCondition() {
            }
        }

        protected Context() {
        }
    }

    private enum Hole implements SurfaceRules.ConditionSource {

        INSTANCE;

        public MapCodec<SurfaceRules.Hole> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Hole.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.Condition apply(SurfaceRules.Context context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Hole.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$Condition;");
        }
    }

    private abstract static class LazyCondition implements SurfaceRules.Condition {

        protected LazyCondition(SurfaceRules.Context context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$LazyCondition.<init>:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)V");
        }

        public boolean test() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$LazyCondition.test:()Z");
        }

        protected abstract long getContextLastUpdate();

        protected abstract boolean compute();

        protected LazyCondition() {
        }
    }

    private abstract static class LazyXZCondition extends SurfaceRules.LazyCondition {

        protected LazyXZCondition(SurfaceRules.Context context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$LazyXZCondition.<init>:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)V");
        }

        protected long getContextLastUpdate() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$LazyXZCondition.getContextLastUpdate:()J");
        }

        protected LazyXZCondition() {
        }
    }

    private abstract static class LazyYCondition extends SurfaceRules.LazyCondition {

        protected LazyYCondition(SurfaceRules.Context context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$LazyYCondition.<init>:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)V");
        }

        protected long getContextLastUpdate() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$LazyYCondition.getContextLastUpdate:()J");
        }

        protected LazyYCondition() {
        }
    }

    private record NoiseThresholdConditionSource(ResourceKey<NormalNoise.NoiseParameters> noise, double minThreshold, double maxThreshold, boolean is3d) implements SurfaceRules.ConditionSource {

        public MapCodec<SurfaceRules.NoiseThresholdConditionSource> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$NoiseThresholdConditionSource.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.Condition apply(SurfaceRules.Context ruleContext) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$NoiseThresholdConditionSource.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$Condition;");
        }
    }

    private record NotCondition(SurfaceRules.Condition target) implements SurfaceRules.Condition {

        public boolean test() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$NotCondition.test:()Z");
        }
    }

    private record NotConditionSource(SurfaceRules.ConditionSource target) implements SurfaceRules.ConditionSource {

        public MapCodec<SurfaceRules.NotConditionSource> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$NotConditionSource.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.Condition apply(SurfaceRules.Context context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$NotConditionSource.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$Condition;");
        }
    }

    public interface RuleSource extends Function<SurfaceRules.Context, SurfaceRules.SurfaceRule> {

        MapCodec<? extends SurfaceRules.RuleSource> codec();
    }

    private record SequenceRule(List<SurfaceRules.SurfaceRule> rules) implements SurfaceRules.SurfaceRule {

        public BlockState tryApply(int blockX, int blockY, int blockZ) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$SequenceRule.tryApply:(III)Lnet/minecraft/world/level/block/state/BlockState;");
        }
    }

    private record SequenceRuleSource(List<SurfaceRules.RuleSource> sequence) implements SurfaceRules.RuleSource {

        public MapCodec<SurfaceRules.SequenceRuleSource> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$SequenceRuleSource.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.SurfaceRule apply(SurfaceRules.Context context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$SequenceRuleSource.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$SurfaceRule;");
        }
    }

    private record StateRule(BlockState state) implements SurfaceRules.SurfaceRule {

        public BlockState tryApply(int blockX, int blockY, int blockZ) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$StateRule.tryApply:(III)Lnet/minecraft/world/level/block/state/BlockState;");
        }
    }

    private enum Steep implements SurfaceRules.ConditionSource {

        INSTANCE;

        public MapCodec<SurfaceRules.Steep> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Steep.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.Condition apply(SurfaceRules.Context context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Steep.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$Condition;");
        }
    }

    private record StoneDepthCheck(int offset, boolean addSurfaceDepth, int secondaryDepthRange, CaveSurface surfaceType) implements SurfaceRules.ConditionSource {

        public MapCodec<SurfaceRules.StoneDepthCheck> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$StoneDepthCheck.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.Condition apply(SurfaceRules.Context ruleContext) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$StoneDepthCheck.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$Condition;");
        }
    }

    protected interface SurfaceRule {

        BlockState tryApply(final int blockX, final int blockY, final int blockZ);
    }

    private enum Temperature implements SurfaceRules.ConditionSource {

        INSTANCE;

        public MapCodec<SurfaceRules.Temperature> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Temperature.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.Condition apply(SurfaceRules.Context context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$Temperature.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$Condition;");
        }
    }

    private record TestRule(SurfaceRules.Condition condition, SurfaceRules.SurfaceRule followup) implements SurfaceRules.SurfaceRule {

        public BlockState tryApply(int blockX, int blockY, int blockZ) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$TestRule.tryApply:(III)Lnet/minecraft/world/level/block/state/BlockState;");
        }
    }

    private record TestRuleSource(SurfaceRules.ConditionSource ifTrue, SurfaceRules.RuleSource thenRun) implements SurfaceRules.RuleSource {

        public MapCodec<SurfaceRules.TestRuleSource> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$TestRuleSource.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.SurfaceRule apply(SurfaceRules.Context context) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$TestRuleSource.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$SurfaceRule;");
        }
    }

    private record VerticalGradientConditionSource(Identifier randomName, VerticalAnchor trueAtAndBelow, VerticalAnchor falseAtAndAbove) implements SurfaceRules.ConditionSource {

        public MapCodec<SurfaceRules.VerticalGradientConditionSource> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$VerticalGradientConditionSource.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.Condition apply(SurfaceRules.Context ruleContext) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$VerticalGradientConditionSource.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$Condition;");
        }
    }

    private record WaterConditionSource(int offset, int surfaceDepthMultiplier, boolean addStoneDepth) implements SurfaceRules.ConditionSource {

        public MapCodec<SurfaceRules.WaterConditionSource> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$WaterConditionSource.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.Condition apply(SurfaceRules.Context ruleContext) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$WaterConditionSource.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$Condition;");
        }
    }

    private record YConditionSource(VerticalAnchor anchor, int surfaceDepthMultiplier, boolean addStoneDepth) implements SurfaceRules.ConditionSource {

        public MapCodec<SurfaceRules.YConditionSource> codec() {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$YConditionSource.codec:()Lcom/mojang/serialization/MapCodec;");
        }

        public SurfaceRules.Condition apply(SurfaceRules.Context ruleContext) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules$YConditionSource.apply:(Lnet/minecraft/world/level/levelgen/SurfaceRules$Context;)Lnet/minecraft/world/level/levelgen/SurfaceRules$Condition;");
        }
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/world/level/levelgen/SurfaceRules");
        }
    }
}
