package net.minecraft.server.level;

import java.util.concurrent.Executor;
import java.util.function.IntConsumer;
import java.util.function.IntSupplier;
import net.minecraft.util.thread.TaskScheduler;
import net.minecraft.world.level.ChunkPos;
import dev.pumpkin.shim.Unimplemented;

public class ChunkTaskDispatcher implements ChunkHolder.LevelChangeListener, AutoCloseable {

    public ChunkTaskDispatcher(TaskScheduler<Runnable> executor, Executor dispatcherExecutor) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkTaskDispatcher.<init>:(Lnet/minecraft/util/thread/TaskScheduler;Ljava/util/concurrent/Executor;)V");
    }

    public void onLevelChange(ChunkPos pos, IntSupplier oldLevel, int newLevel, IntConsumer setQueueLevel) {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkTaskDispatcher.onLevelChange:(Lnet/minecraft/world/level/ChunkPos;Ljava/util/function/IntSupplier;ILjava/util/function/IntConsumer;)V");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/server/level/ChunkTaskDispatcher.close:()V");
    }

    public ChunkTaskDispatcher() {
    }
}
