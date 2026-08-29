package net.minecraft.world.entity.ai.goal;

import java.util.function.Function;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.PathfinderMob;
import dev.pumpkin.shim.Unimplemented;

public class PanicGoal extends Goal {

    public PanicGoal(PathfinderMob mob, double speedModifier) {
    }

    public PanicGoal(PathfinderMob mob, double speedModifier, TagKey<DamageType> panicCausingDamageTypes) {
    }

    public PanicGoal(PathfinderMob mob, double speedModifier, Function<PathfinderMob, TagKey<DamageType>> panicCausingDamageTypes) {
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

    public PanicGoal() {
    }
}
