package net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class CommandBlockEntity extends BlockEntity {

    public CommandBlockEntity(BlockPos worldPosition, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/CommandBlockEntity.<init>:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    protected void saveAdditional(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/CommandBlockEntity.saveAdditional:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected void loadAdditional(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/CommandBlockEntity.loadAdditional:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected void applyImplicitComponents(DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/CommandBlockEntity.applyImplicitComponents:(Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/CommandBlockEntity.collectImplicitComponents:(Lnet/minecraft/core/component/DataComponentMap$Builder;)V");
    }

    public void removeComponentsFromTag(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/CommandBlockEntity.removeComponentsFromTag:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public enum Mode {

        SEQUENCE, AUTO, REDSTONE
    }

    public CommandBlockEntity() {
    }
}
