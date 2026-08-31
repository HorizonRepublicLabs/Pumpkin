package net.neoforged.neoforge.common.extensions;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import dev.pumpkin.shim.Unimplemented;

public interface IDispensibleContainerItemExtension {

    default boolean emptyContents(LivingEntity entity, Level level, BlockPos pos, BlockHitResult hitResult, ItemStack container) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IDispensibleContainerItemExtension.emptyContents:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/BlockHitResult;Lnet/minecraft/world/item/ItemStack;)Z");
    }
}
