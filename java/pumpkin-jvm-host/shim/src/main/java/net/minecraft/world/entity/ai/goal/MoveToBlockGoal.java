package net.minecraft.world.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.LevelReader;
import dev.pumpkin.shim.Unimplemented;

public abstract class MoveToBlockGoal extends Goal {

    public MoveToBlockGoal(PathfinderMob mob, double speedModifier, int searchRange) {
    }

    public MoveToBlockGoal(PathfinderMob mob, double speedModifier, int searchRange, int verticalSearchRange) {
    }

    public boolean canUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/MoveToBlockGoal.canUse:()Z");
    }

    public boolean canContinueToUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/MoveToBlockGoal.canContinueToUse:()Z");
    }

    public void start() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/MoveToBlockGoal.start:()V");
    }

    public boolean requiresUpdateEveryTick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/MoveToBlockGoal.requiresUpdateEveryTick:()Z");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/MoveToBlockGoal.tick:()V");
    }

    protected abstract boolean isValidTarget(LevelReader level, BlockPos pos);

    public MoveToBlockGoal() {
    }
}
