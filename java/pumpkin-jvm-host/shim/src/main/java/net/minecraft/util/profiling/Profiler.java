package net.minecraft.util.profiling;

import dev.pumpkin.shim.Unimplemented;

public final class Profiler {

    protected Profiler() {
    }

    public static ProfilerFiller get() {
        throw Unimplemented.forMember("net/minecraft/util/profiling/Profiler.get:()Lnet/minecraft/util/profiling/ProfilerFiller;");
    }

    public interface Scope extends AutoCloseable {

        void close();
    }
}
