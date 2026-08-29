package net.minecraft.util.profiling.metrics;

import dev.pumpkin.shim.Unimplemented;

public enum MetricCategory {

    PATH_FINDING,
    EVENT_LOOPS,
    CONSECUTIVE_EXECUTORS,
    TICK_LOOP,
    JVM,
    CHUNK_RENDERING,
    CHUNK_RENDERING_DISPATCHING,
    CPU,
    GPU;

    public String getDescription() {
        throw Unimplemented.forMember("net/minecraft/util/profiling/metrics/MetricCategory.getDescription:()Ljava/lang/String;");
    }
}
