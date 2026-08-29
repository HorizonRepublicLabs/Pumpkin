package net.minecraft.world.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.ContainerOpenersCounter;

public interface ContainerUser {

    boolean hasContainerOpen(final ContainerOpenersCounter container, final BlockPos blockPos);

    double getContainerInteractionRange();
}
