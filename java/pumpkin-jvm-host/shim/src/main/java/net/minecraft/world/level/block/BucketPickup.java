package net.minecraft.world.level.block;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.extensions.IBucketPickupExtension;

public interface BucketPickup extends IBucketPickupExtension {

    ItemStack pickupBlock(LivingEntity user, LevelAccessor level, BlockPos pos, BlockState state);

    Optional<SoundEvent> getPickupSound();
}
