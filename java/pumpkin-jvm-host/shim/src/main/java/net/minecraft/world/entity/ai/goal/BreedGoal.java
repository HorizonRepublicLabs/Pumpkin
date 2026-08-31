package net.minecraft.world.entity.ai.goal;

import net.minecraft.world.entity.animal.Animal;
import dev.pumpkin.shim.Unimplemented;

public class BreedGoal extends Goal {

    public BreedGoal(Animal animal, double speedModifier) {
    }

    public BreedGoal(Animal animal, double speedModifier, Class<? extends Animal> clazz) {
    }

    public boolean canUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/BreedGoal.canUse:()Z");
    }

    public boolean canContinueToUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/BreedGoal.canContinueToUse:()Z");
    }

    public void stop() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/BreedGoal.stop:()V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/BreedGoal.tick:()V");
    }

    public BreedGoal() {
    }
}
