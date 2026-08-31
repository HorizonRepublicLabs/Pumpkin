package net.minecraft.world.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.common.extensions.IDispensibleContainerItemExtension;
import dev.pumpkin.shim.Unimplemented;

public interface DispensibleContainerItem extends IDispensibleContainerItemExtension {

    default void checkExtraContent(LivingEntity user, Level level, ItemStack itemStack, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/item/DispensibleContainerItem.checkExtraContent:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/core/BlockPos;)V");
    }

    boolean emptyContents(final LivingEntity user, final Level level, final BlockPos pos, final BlockHitResult hitResult);
}
