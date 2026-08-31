package net.minecraft.world.inventory;

import java.util.List;
import net.minecraft.resources.Identifier;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class InventoryMenu extends AbstractCraftingMenu {

    public static final Identifier EMPTY_ARMOR_SLOT_HELMET = null;

    public static final Identifier EMPTY_ARMOR_SLOT_CHESTPLATE = null;

    public static final Identifier EMPTY_ARMOR_SLOT_LEGGINGS = null;

    public static final Identifier EMPTY_ARMOR_SLOT_BOOTS = null;

    public static final Identifier EMPTY_ARMOR_SLOT_SHIELD = null;

    public InventoryMenu(Inventory inventory, boolean active, Player owner) {
    }

    public void slotsChanged(Container container) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/InventoryMenu.slotsChanged:(Lnet/minecraft/world/Container;)V");
    }

    public void removed(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/InventoryMenu.removed:(Lnet/minecraft/world/entity/player/Player;)V");
    }

    public boolean stillValid(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/InventoryMenu.stillValid:(Lnet/minecraft/world/entity/player/Player;)Z");
    }

    public ItemStack quickMoveStack(Player player, int slotIndex) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/InventoryMenu.quickMoveStack:(Lnet/minecraft/world/entity/player/Player;I)Lnet/minecraft/world/item/ItemStack;");
    }

    public boolean canTakeItemForPickAll(ItemStack carried, Slot target) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/InventoryMenu.canTakeItemForPickAll:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/inventory/Slot;)Z");
    }

    public Slot getResultSlot() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/InventoryMenu.getResultSlot:()Lnet/minecraft/world/inventory/Slot;");
    }

    public List<Slot> getInputGridSlots() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/InventoryMenu.getInputGridSlots:()Ljava/util/List;");
    }

    public RecipeBookType getRecipeBookType() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/InventoryMenu.getRecipeBookType:()Lnet/minecraft/world/inventory/RecipeBookType;");
    }

    protected Player owner() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/InventoryMenu.owner:()Lnet/minecraft/world/entity/player/Player;");
    }

    public InventoryMenu() {
    }
}
