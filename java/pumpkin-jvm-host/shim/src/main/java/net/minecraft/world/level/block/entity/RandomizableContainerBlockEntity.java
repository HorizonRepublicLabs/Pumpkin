package net.minecraft.world.level.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.RandomizableContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.level.storage.loot.LootTable;
import dev.pumpkin.shim.Unimplemented;

public abstract class RandomizableContainerBlockEntity extends BaseContainerBlockEntity implements RandomizableContainer {

    protected RandomizableContainerBlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.<init>:(Lnet/minecraft/world/level/block/entity/BlockEntityType;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");
    }

    public ResourceKey<LootTable> getLootTable() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.getLootTable:()Lnet/minecraft/resources/ResourceKey;");
    }

    public void setLootTable(ResourceKey<LootTable> lootTable) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.setLootTable:(Lnet/minecraft/resources/ResourceKey;)V");
    }

    public long getLootTableSeed() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.getLootTableSeed:()J");
    }

    public void setLootTableSeed(long lootTableSeed) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.setLootTableSeed:(J)V");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.isEmpty:()Z");
    }

    public ItemStack getItem(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.getItem:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack removeItem(int slot, int count) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.removeItem:(II)Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack removeItemNoUpdate(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.removeItemNoUpdate:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public void setItem(int slot, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.setItem:(ILnet/minecraft/world/item/ItemStack;)V");
    }

    public boolean canOpen(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.canOpen:(Lnet/minecraft/world/entity/player/Player;)Z");
    }

    public AbstractContainerMenu createMenu(int containerId, Inventory inventory, Player player) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.createMenu:(ILnet/minecraft/world/entity/player/Inventory;Lnet/minecraft/world/entity/player/Player;)Lnet/minecraft/world/inventory/AbstractContainerMenu;");
    }

    protected void applyImplicitComponents(DataComponentGetter components) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.applyImplicitComponents:(Lnet/minecraft/core/component/DataComponentGetter;)V");
    }

    protected void collectImplicitComponents(DataComponentMap.Builder components) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.collectImplicitComponents:(Lnet/minecraft/core/component/DataComponentMap$Builder;)V");
    }

    public void removeComponentsFromTag(ValueOutput output) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/RandomizableContainerBlockEntity.removeComponentsFromTag:(Lnet/minecraft/world/level/storage/ValueOutput;)V");
    }

    public RandomizableContainerBlockEntity() {
    }
}
