package net.minecraft.commands.execution;

import net.minecraft.util.profiling.ProfilerFiller;
import dev.pumpkin.shim.Unimplemented;

public class ExecutionContext<T> implements AutoCloseable {

    public ExecutionContext(int commandLimit, int forkLimit, ProfilerFiller profiler) {
        throw Unimplemented.forMember("net/minecraft/commands/execution/ExecutionContext.<init>:(IILnet/minecraft/util/profiling/ProfilerFiller;)V");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/commands/execution/ExecutionContext.close:()V");
    }

    public ExecutionContext() {
    }
}
