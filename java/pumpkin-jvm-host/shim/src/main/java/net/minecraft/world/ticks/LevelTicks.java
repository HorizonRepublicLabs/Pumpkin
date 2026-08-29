package net.minecraft.world.ticks;

import java.util.function.LongPredicate;
import net.minecraft.core.BlockPos;
import dev.pumpkin.shim.Unimplemented;

public class LevelTicks<T> implements LevelTickAccess<T> {

    public LevelTicks(LongPredicate tickCheck) {
    }

    public void schedule(ScheduledTick<T> tick) {
        throw Unimplemented.forMember("net/minecraft/world/ticks/LevelTicks.schedule:(Lnet/minecraft/world/ticks/ScheduledTick;)V");
    }

    public boolean hasScheduledTick(BlockPos pos, T block) {
        throw Unimplemented.forMember("net/minecraft/world/ticks/LevelTicks.hasScheduledTick:(Lnet/minecraft/core/BlockPos;Ljava/lang/Object;)Z");
    }

    public boolean willTickThisTick(BlockPos pos, T type) {
        throw Unimplemented.forMember("net/minecraft/world/ticks/LevelTicks.willTickThisTick:(Lnet/minecraft/core/BlockPos;Ljava/lang/Object;)Z");
    }

    public int count() {
        throw Unimplemented.forMember("net/minecraft/world/ticks/LevelTicks.count:()I");
    }

    private interface PosAndContainerConsumer<T> {

        void accept(long pos, LevelChunkTicks<T> container);
    }

    public LevelTicks() {
    }
}
