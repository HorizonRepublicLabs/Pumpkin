package net.minecraft.world.ticks;

import java.util.List;
import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public class ProtoChunkTicks<T> implements TickContainerAccess<T>, SerializableTickContainer<T> {

    public void schedule(ScheduledTick<T> tick) {
        throw Unimplemented.forMember("net/minecraft/world/ticks/ProtoChunkTicks.schedule:(Lnet/minecraft/world/ticks/ScheduledTick;)V");
    }

    private void schedule(SavedTick<T> newTick) {
        throw Unimplemented.forMember("net/minecraft/world/ticks/ProtoChunkTicks.schedule:(Lnet/minecraft/world/ticks/SavedTick;)V");
    }

    public boolean hasScheduledTick(BlockPos pos, T type) {
        throw Unimplemented.forMember("net/minecraft/world/ticks/ProtoChunkTicks.hasScheduledTick:(Lnet/minecraft/core/BlockPos;Ljava/lang/Object;)Z");
    }

    public int count() {
        throw Unimplemented.forMember("net/minecraft/world/ticks/ProtoChunkTicks.count:()I");
    }

    public List<SavedTick<T>> pack(long currentTick) {
        throw Unimplemented.forMember("net/minecraft/world/ticks/ProtoChunkTicks.pack:(J)Ljava/util/List;");
    }

    public static <T> ProtoChunkTicks<T> load(List<SavedTick<T>> ticks) {
        throw Unimplemented.forMember("net/minecraft/world/ticks/ProtoChunkTicks.load:(Ljava/util/List;)Lnet/minecraft/world/ticks/ProtoChunkTicks;");
    }

    public ProtoChunkTicks() {
    }
}
