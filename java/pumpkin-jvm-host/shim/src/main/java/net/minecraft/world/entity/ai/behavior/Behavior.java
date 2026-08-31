package net.minecraft.world.entity.ai.behavior;

import java.util.Map;
import java.util.Set;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import dev.pumpkin.shim.Unimplemented;

public abstract class Behavior<E extends LivingEntity> implements BehaviorControl<E> {

    public Behavior(Map<MemoryModuleType<?>, MemoryStatus> entryCondition) {
    }

    public Behavior(Map<MemoryModuleType<?>, MemoryStatus> entryCondition, int timeOutDuration) {
    }

    public Behavior(Map<MemoryModuleType<?>, MemoryStatus> entryCondition, int minDuration, int maxDuration) {
    }

    public Behavior.Status getStatus() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/behavior/Behavior.getStatus:()Lnet/minecraft/world/entity/ai/behavior/Behavior$Status;");
    }

    public Set<MemoryModuleType<?>> getRequiredMemories() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/behavior/Behavior.getRequiredMemories:()Ljava/util/Set;");
    }

    public final boolean tryStart(ServerLevel level, E body, long timestamp) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/behavior/Behavior.tryStart:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;J)Z");
    }

    public final void tickOrStop(ServerLevel level, E body, long timestamp) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/behavior/Behavior.tickOrStop:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;J)V");
    }

    public final void doStop(ServerLevel level, E body, long timestamp) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/behavior/Behavior.doStop:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/LivingEntity;J)V");
    }

    public String debugString() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/behavior/Behavior.debugString:()Ljava/lang/String;");
    }

    public enum Status {

        STOPPED, RUNNING
    }

    public Behavior() {
    }
}
