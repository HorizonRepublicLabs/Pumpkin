package net.minecraft.world.entity.ai.goal;

import net.minecraft.world.entity.monster.zombie.Zombie;
import dev.pumpkin.shim.Unimplemented;

public class ZombieAttackGoal extends MeleeAttackGoal {

    public ZombieAttackGoal(Zombie zombie, double speedModifier, boolean trackTarget) {
    }

    public void start() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/ZombieAttackGoal.start:()V");
    }

    public void stop() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/ZombieAttackGoal.stop:()V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/ZombieAttackGoal.tick:()V");
    }

    public ZombieAttackGoal() {
    }
}
