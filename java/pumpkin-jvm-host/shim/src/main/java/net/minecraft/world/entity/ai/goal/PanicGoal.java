package net.minecraft.world.entity.ai.goal;

import java.util.function.Function;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.PathfinderMob;
import dev.pumpkin.shim.Unimplemented;

public class PanicGoal extends Goal {

    public PanicGoal(PathfinderMob mob, double speedModifier) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/PanicGoal.<init>:(Lnet/minecraft/world/entity/PathfinderMob;D)V");
    }

    public PanicGoal(PathfinderMob mob, double speedModifier, TagKey<DamageType> panicCausingDamageTypes) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/PanicGoal.<init>:(Lnet/minecraft/world/entity/PathfinderMob;DLnet/minecraft/tags/TagKey;)V");
    }

    public PanicGoal(PathfinderMob mob, double speedModifier, Function<PathfinderMob, TagKey<DamageType>> panicCausingDamageTypes) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/PanicGoal.<init>:(Lnet/minecraft/world/entity/PathfinderMob;DLjava/util/function/Function;)V");
    }

    public boolean canUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/PanicGoal.canUse:()Z");
    }

    public void start() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/PanicGoal.start:()V");
    }

    public void stop() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/PanicGoal.stop:()V");
    }

    public boolean canContinueToUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/PanicGoal.canContinueToUse:()Z");
    }

    protected PanicGoal() {
    }
}
