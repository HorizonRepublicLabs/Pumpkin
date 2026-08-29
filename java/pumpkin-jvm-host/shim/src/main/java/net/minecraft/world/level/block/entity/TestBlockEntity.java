package net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class TestBlockEntity extends BlockEntity {

    public TestBlockEntity(BlockPos worldPosition, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestBlockEntity.<init>:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    protected void saveAdditional(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestBlockEntity.saveAdditional:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void loadAdditional(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestBlockEntity.loadAdditional:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestBlockEntity.getUpdateTag:(Lnet/minecraft/core/HolderLookup$Provider;)Lnet/minecraft/nbt/CompoundTag;");
    }

    public void reset() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/TestBlockEntity.reset:()V");
    }

    protected TestBlockEntity() {
    }
}
