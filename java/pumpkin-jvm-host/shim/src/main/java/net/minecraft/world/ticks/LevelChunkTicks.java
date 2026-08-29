package net.minecraft.world.ticks;

import java.util.List;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public class LevelChunkTicks<T> implements TickContainerAccess<T>, SerializableTickContainer<T> {

    public LevelChunkTicks() {
        throw Unimplemented.forMember("net/minecraft/world/ticks/LevelChunkTicks.<init>:()V");
    }

    public LevelChunkTicks(List<SavedTick<T>> pendingTicks) {
        throw Unimplemented.forMember("net/minecraft/world/ticks/LevelChunkTicks.<init>:(Ljava/util/List;)V");
    }

    public void schedule(ScheduledTick<T> tick) {
        throw Unimplemented.forMember("net/minecraft/world/ticks/LevelChunkTicks.schedule:(Lnet/minecraft/world/ticks/ScheduledTick;)V");
    }

    public boolean hasScheduledTick(BlockPos pos, T type) {
        throw Unimplemented.forMember("net/minecraft/world/ticks/LevelChunkTicks.hasScheduledTick:(Lnet/minecraft/core/BlockPos;Ljava/lang/Object;)Z");
    }

    public Stream<ScheduledTick<T>> getAll() {
        throw Unimplemented.forMember("net/minecraft/world/ticks/LevelChunkTicks.getAll:()Ljava/util/stream/Stream;");
    }

    public int count() {
        throw Unimplemented.forMember("net/minecraft/world/ticks/LevelChunkTicks.count:()I");
    }

    public List<SavedTick<T>> pack(long currentTick) {
        throw Unimplemented.forMember("net/minecraft/world/ticks/LevelChunkTicks.pack:(J)Ljava/util/List;");
    }

    public void unpack(long currentTick) {
        throw Unimplemented.forMember("net/minecraft/world/ticks/LevelChunkTicks.unpack:(J)V");
    }
}
