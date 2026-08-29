package net.minecraft.util.profiling.metrics;

import it.unimi.dsi.fastutil.ints.Int2DoubleMap;
import java.util.function.DoubleSupplier;
import java.util.function.ToDoubleFunction;
import dev.pumpkin.shim.Unimplemented;

public class MetricSampler {

    protected MetricSampler(String name, MetricSampler.SamplingPhase samplingPhase, MetricCategory category, DoubleSupplier sampler, Runnable beforeTick, MetricSampler.ThresholdTest thresholdTest) {
        throw Unimplemented.forMember("net/minecraft/util/profiling/metrics/MetricSampler.<init>:(Ljava/lang/String;Lnet/minecraft/util/profiling/metrics/MetricSampler$SamplingPhase;Lnet/minecraft/util/profiling/metrics/MetricCategory;Ljava/util/function/DoubleSupplier;Ljava/lang/Runnable;Lnet/minecraft/util/profiling/metrics/MetricSampler$ThresholdTest;)V");
    }

    public static MetricSampler create(String name, MetricCategory category, DoubleSupplier sampler) {
        throw Unimplemented.forMember("net/minecraft/util/profiling/metrics/MetricSampler.create:(Ljava/lang/String;Lnet/minecraft/util/profiling/metrics/MetricCategory;Ljava/util/function/DoubleSupplier;)Lnet/minecraft/util/profiling/metrics/MetricSampler;");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/util/profiling/metrics/MetricSampler.getName:()Ljava/lang/String;");
    }

    public MetricSampler.SamplerResult result() {
        throw Unimplemented.forMember("net/minecraft/util/profiling/metrics/MetricSampler.result:()Lnet/minecraft/util/profiling/metrics/MetricSampler$SamplerResult;");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/util/profiling/metrics/MetricSampler.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/util/profiling/metrics/MetricSampler.hashCode:()I");
    }

    public static class MetricSamplerBuilder<T> {

        public MetricSamplerBuilder(String name, MetricCategory category, ToDoubleFunction<T> sampler, T context) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/metrics/MetricSampler$MetricSamplerBuilder.<init>:(Ljava/lang/String;Lnet/minecraft/util/profiling/metrics/MetricCategory;Ljava/util/function/ToDoubleFunction;Ljava/lang/Object;)V");
        }

        public MetricSampler build() {
            throw Unimplemented.forMember("net/minecraft/util/profiling/metrics/MetricSampler$MetricSamplerBuilder.build:()Lnet/minecraft/util/profiling/metrics/MetricSampler;");
        }

        protected MetricSamplerBuilder() {
        }
    }

    public static class SamplerResult {

        public SamplerResult(int firstTick, int lastTick, Int2DoubleMap recording) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/metrics/MetricSampler$SamplerResult.<init>:(IILit/unimi/dsi/fastutil/ints/Int2DoubleMap;)V");
        }

        protected SamplerResult() {
        }
    }

    public enum SamplingPhase {

        EXTRACT, END_TICK
    }

    public interface ThresholdTest {

        boolean test(final double value);
    }

    public static class ValueIncreasedByPercentage implements MetricSampler.ThresholdTest {

        public ValueIncreasedByPercentage(float percentageIncreaseThreshold) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/metrics/MetricSampler$ValueIncreasedByPercentage.<init>:(F)V");
        }

        public boolean test(double value) {
            throw Unimplemented.forMember("net/minecraft/util/profiling/metrics/MetricSampler$ValueIncreasedByPercentage.test:(D)Z");
        }

        protected ValueIncreasedByPercentage() {
        }
    }

    protected MetricSampler() {
    }
}
