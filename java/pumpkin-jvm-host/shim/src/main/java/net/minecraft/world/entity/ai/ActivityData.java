package net.minecraft.world.entity.ai;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import java.util.Set;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.behavior.BehaviorControl;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.memory.MemoryStatus;
import net.minecraft.world.entity.schedule.Activity;
import dev.pumpkin.shim.Unimplemented;

public record ActivityData<E extends LivingEntity>(Activity activityType, ImmutableList<? extends Pair<Integer, ? extends BehaviorControl<? super E>>> behaviorPriorityPairs, Set<Pair<MemoryModuleType<?>, MemoryStatus>> conditions, Set<MemoryModuleType<?>> memoriesToEraseWhenStopped) {

    public static <E extends LivingEntity> ActivityData<E> create(Activity activity, int priorityOfFirstBehavior, ImmutableList<? extends BehaviorControl<? super E>> behaviorList) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/ActivityData.create:(Lnet/minecraft/world/entity/schedule/Activity;ILcom/google/common/collect/ImmutableList;)Lnet/minecraft/world/entity/ai/ActivityData;");
    }

    public static <E extends LivingEntity> ActivityData<E> create(Activity activity, int priorityOfFirstBehavior, ImmutableList<? extends BehaviorControl<? super E>> behaviorList, MemoryModuleType<?> memoryThatMustHaveValueAndWillBeErasedAfter) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/ActivityData.create:(Lnet/minecraft/world/entity/schedule/Activity;ILcom/google/common/collect/ImmutableList;Lnet/minecraft/world/entity/ai/memory/MemoryModuleType;)Lnet/minecraft/world/entity/ai/ActivityData;");
    }

    public static <E extends LivingEntity> ActivityData<E> create(Activity activity, ImmutableList<? extends Pair<Integer, ? extends BehaviorControl<? super E>>> behaviorPriorityPairs) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/ActivityData.create:(Lnet/minecraft/world/entity/schedule/Activity;Lcom/google/common/collect/ImmutableList;)Lnet/minecraft/world/entity/ai/ActivityData;");
    }

    public static <E extends LivingEntity> ActivityData<E> create(Activity activity, int priorityOfFirstBehavior, ImmutableList<? extends BehaviorControl<? super E>> behaviorList, Set<Pair<MemoryModuleType<?>, MemoryStatus>> conditions) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/ActivityData.create:(Lnet/minecraft/world/entity/schedule/Activity;ILcom/google/common/collect/ImmutableList;Ljava/util/Set;)Lnet/minecraft/world/entity/ai/ActivityData;");
    }

    public static <E extends LivingEntity> ActivityData<E> create(Activity activity, ImmutableList<? extends Pair<Integer, ? extends BehaviorControl<? super E>>> behaviorPriorityPairs, Set<Pair<MemoryModuleType<?>, MemoryStatus>> conditions) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/ActivityData.create:(Lnet/minecraft/world/entity/schedule/Activity;Lcom/google/common/collect/ImmutableList;Ljava/util/Set;)Lnet/minecraft/world/entity/ai/ActivityData;");
    }

    public static <E extends LivingEntity> ActivityData<E> create(Activity activity, ImmutableList<? extends Pair<Integer, ? extends BehaviorControl<? super E>>> behaviorPriorityPairs, Set<Pair<MemoryModuleType<?>, MemoryStatus>> conditions, Set<MemoryModuleType<?>> memoriesToEraseWhenStopped) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/ActivityData.create:(Lnet/minecraft/world/entity/schedule/Activity;Lcom/google/common/collect/ImmutableList;Ljava/util/Set;Ljava/util/Set;)Lnet/minecraft/world/entity/ai/ActivityData;");
    }
}
