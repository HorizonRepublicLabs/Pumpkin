package net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Container;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.Nameable;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import dev.pumpkin.shim.Unimplemented;

public abstract class BaseContainerBlockEntity extends BlockEntity implements Container, MenuProvider, Nameable {

    protected BaseContainerBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
    }

    protected void loadAdditional(ValueInput input) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.loadAdditional:(Lnet/minecraft/world/level/storage/ValueInput;)V");
    }

    protected void saveAdditional(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.saveAdditional:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public Component getName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.getName:()Lnet/minecraft/network/chat/Component;");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public Component getCustomName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.getCustomName:()Lnet/minecraft/network/chat/Component;");
    }

    protected abstract Component getDefaultName();

    public boolean isLocked() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.isLocked:()Z");
    }

    protected abstract NonNullList<ItemStack> getItems();

    protected abstract void setItems(NonNullList<ItemStack> items);

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.isEmpty:()Z");
    }

    public ItemStack getItem(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.getItem:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack removeItem(int slot, int count) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.removeItem:(II)Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack removeItemNoUpdate(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.removeItemNoUpdate:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public void setItem(int slot, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.setItem:(ILnet/minecraft/world/item/ItemStack;)V");
    }

    public void setItem(int slot, ItemStack itemStack, boolean insideTransaction) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.setItem:(ILnet/minecraft/world/item/ItemStack;Z)V");
    }

    public boolean stillValid(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.stillValid:(Lnet/minecraft/world/entity/player/Player;)Z");
    }

    public void clearContent() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.clearContent:()V");
    }

    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.createMenu:(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/inventory/AbstractContainerMenu;");
    }

    protected abstract AbstractContainerMenu createMenu(final int containerId, final Inventory inventory);

    protected void applyImplicitComponents(DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.applyImplicitComponents:(Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.collectImplicitComponents:(Lnet/minecraft/core/component/DataComponentMap$Builder;)V");
    }

    public void removeComponentsFromTag(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BaseContainerBlockEntity.removeComponentsFromTag:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public BaseContainerBlockEntity() {
    }
}
