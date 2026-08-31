package net.minecraft.world.inventory;

import java.util.Optional;
import net.minecraft.resources.Identifier;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class Slot {

    private final int slot = 0;

    // Pumpkin divergence: a slot really points at its container and position; the menu
    // machinery reads all four.
    public final Container container;

    public int index;

    public final int x;

    public final int y;

    private final int pumpkinContainerSlot;

    public int pumpkinContainerSlot() {
        return pumpkinContainerSlot;
    }

    public Slot(Container container, int slot, int x, int y) {
        this.container = container;
        this.pumpkinContainerSlot = slot;
        this.x = x;
        this.y = y;
    }

    public void onQuickCraft(ItemStack picked, ItemStack original) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.onQuickCraft:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)V");
    }

    protected void onQuickCraft(ItemStack picked, int count) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.onQuickCraft:(Lnet/minecraft/world/item/ItemStack;I)V");
    }

    protected void checkTakeAchievements(ItemStack carried) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.checkTakeAchievements:(Lnet/minecraft/world/item/ItemStack;)V");
    }

    public void onTake(Player player, ItemStack carried) {
        // Vanilla hooks crafting stats here; the base has nothing to do.
    }

    // Pumpkin divergence: vanilla bodies for the click machinery. mayPlace/mayPickup
    // default open, exactly as vanilla's base slot does; subclasses narrow them.
    public boolean mayPlace(ItemStack itemStack) {
        return true;
    }

    public boolean mayPickup(net.minecraft.world.entity.player.Player player) {
        return true;
    }

    // Pumpkin divergence: vanilla body -- read through to the container.
    public ItemStack getItem() {
        return container.getItem(pumpkinContainerSlot);
    }

    public boolean hasItem() {
        return !getItem().isEmpty();
    }

    public void setByPlayer(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.setByPlayer:(Lnet/minecraft/world/item/ItemStack;)V");
    }

    public void setByPlayer(ItemStack itemStack, ItemStack previous) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.setByPlayer:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemStack;)V");
    }

    public void set(ItemStack itemStack) {
        container.setItem(pumpkinContainerSlot, itemStack);
        setChanged();
    }

    public void setChanged() {
        // Nothing to mark: the click bridge serialises the menu's state after every
        // click regardless.
    }

    public int getMaxStackSize() {
        return 64;
    }

    public int getMaxStackSize(ItemStack itemStack) {
        return getMaxStackSize();
    }

    public ItemStack remove(int amount) {
        ItemStack current = getItem();
        if (current.isEmpty() || amount <= 0) {
            return ItemStack.EMPTY;
        }
        int taken = Math.min(amount, current.count());
        ItemStack removed = current.copyWithCount(taken);
        set(current.count() == taken ? ItemStack.EMPTY
                : current.copyWithCount(current.count() - taken));
        return removed;
    }

    public boolean isActive() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.isActive:()Z");
    }

    // Pumpkin divergence: NeoForge's own accessor -- the index inside the backing
    // container, which the ctor stored.
    public int getSlotIndex() {
        return pumpkinContainerSlot;
    }

    public Slot setBackground(Identifier sprite) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.setBackground:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/world/inventory/Slot;");
    }

    public Optional<ItemStack> tryRemove(int amount, int maxAmount, Player player) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.tryRemove:(IILnet/minecraft/world/entity/player/Player;)Ljava/util/Optional;");
    }

    public boolean isHighlightable() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.isHighlightable:()Z");
    }

    public Slot() {
        this.container = null;
        this.pumpkinContainerSlot = 0;
        this.x = 0;
        this.y = 0;
    }
}
