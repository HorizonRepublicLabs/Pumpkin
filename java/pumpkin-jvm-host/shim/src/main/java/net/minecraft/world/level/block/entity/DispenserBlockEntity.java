package net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public class DispenserBlockEntity extends RandomizableContainerBlockEntity {

    protected DispenserBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
    }

    public DispenserBlockEntity(BlockPos worldPosition, BlockState blockState) {
    }

    public int getContainerSize() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/DispenserBlockEntity.getContainerSize:()I");
    }

    protected Component getDefaultName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/DispenserBlockEntity.getDefaultName:()Lnet/minecraft/network/chat/Component;");
    }

    protected void loadAdditional(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/DispenserBlockEntity.loadAdditional:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected void saveAdditional(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/DispenserBlockEntity.saveAdditional:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    protected NonNullList<ItemStack> getItems() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/DispenserBlockEntity.getItems:()Lnet/minecraft/core/NonNullList;");
    }

    protected void setItems(NonNullList<ItemStack> items) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/DispenserBlockEntity.setItems:(Lnet/minecraft/core/NonNullList;)V");
    }

    protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/DispenserBlockEntity.createMenu:(ILnet/minecraft/world/entity/player/Inventory;)Lnet/minecraft/world/inventory/AbstractContainerMenu;");
    }

    public DispenserBlockEntity() {
    }
}
