package net.minecraft.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.extensions.IDispensibleContainerItemExtension;

public interface DispensibleContainerItem extends IDispensibleContainerItemExtension {

    boolean emptyContents(final LivingEntity user, final Level level, final BlockPos pos, final BlockHitResult hitResult);
}
