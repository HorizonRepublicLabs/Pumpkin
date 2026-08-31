package net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public abstract class ContainerOpenersCounter {

    protected abstract void onOpen(final Level level, final BlockPos pos, final BlockState blockState);

    protected abstract void onClose(final Level level, final BlockPos pos, final BlockState blockState);

    protected abstract void openerCountChanged(final Level level, final BlockPos pos, final BlockState blockState, int previous, int current);

    public abstract boolean isOwnContainer(final Player player);

    public void incrementOpeners(LivingEntity entity, Level level, BlockPos pos, BlockState blockState, double maxInteractionRange) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/ContainerOpenersCounter.incrementOpeners:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;D)V");
    }

    public void decrementOpeners(LivingEntity entity, Level level, BlockPos pos, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/ContainerOpenersCounter.decrementOpeners:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    private boolean hasContainerOpen(Entity entity, BlockPos blockPos) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/ContainerOpenersCounter.hasContainerOpen:(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/core/BlockPos;)Z");
    }

    public void recheckOpeners(Level level, BlockPos pos, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/ContainerOpenersCounter.recheckOpeners:(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public ContainerOpenersCounter() {
    }
}
