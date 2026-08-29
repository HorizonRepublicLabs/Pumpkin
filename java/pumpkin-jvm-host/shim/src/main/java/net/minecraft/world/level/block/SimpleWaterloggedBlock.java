package net.minecraft.world.level.block;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import dev.pumpkin.shim.Unimplemented;

public interface SimpleWaterloggedBlock extends BucketPickup, LiquidBlockContainer {

    default boolean canPlaceLiquid(LivingEntity user, BlockGetter level, BlockPos pos, BlockState state, Fluid type) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SimpleWaterloggedBlock.canPlaceLiquid:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/material/Fluid;)Z");
    }

    default boolean placeLiquid(LevelAccessor level, BlockPos pos, BlockState state, FluidState fluidState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SimpleWaterloggedBlock.placeLiquid:(Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/material/FluidState;)Z");
    }

    default ItemStack pickupBlock(LivingEntity user, LevelAccessor level, BlockPos pos, BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SimpleWaterloggedBlock.pickupBlock:(Lnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/level/LevelAccessor;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/item/ItemStack;");
    }

    default Optional<SoundEvent> getPickupSound() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SimpleWaterloggedBlock.getPickupSound:()Ljava/util/Optional;");
    }
}
