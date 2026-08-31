package net.minecraft.world.entity.ai.goal;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import dev.pumpkin.shim.Unimplemented;

public class RemoveBlockGoal extends MoveToBlockGoal {

    public RemoveBlockGoal(Block blockToRemove, PathfinderMob mob, double speedModifier, int verticalSearchRange) {
    }

    public boolean canUse() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RemoveBlockGoal.canUse:()Z");
    }

    public void stop() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RemoveBlockGoal.stop:()V");
    }

    public void start() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RemoveBlockGoal.start:()V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RemoveBlockGoal.tick:()V");
    }

    protected boolean isValidTarget(LevelReader level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/goal/RemoveBlockGoal.isValidTarget:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)Z");
    }

    public RemoveBlockGoal() {
    }
}
