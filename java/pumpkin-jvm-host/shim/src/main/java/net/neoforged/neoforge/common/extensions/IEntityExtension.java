package net.neoforged.neoforge.common.extensions;

import java.util.Collection;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.block.state.BlockState;

public interface IEntityExtension {

    Collection<ItemEntity> captureDrops();

    Collection<ItemEntity> captureDrops(Collection<ItemEntity> captureDrops);

    CompoundTag getPersistentData();

    boolean canTrample(ServerLevel level, BlockState state, BlockPos pos, double fallDistance);

    boolean isAddedToLevel();

    void onAddedToLevel();

    void onRemovedFromLevel();

    void revive();
}
