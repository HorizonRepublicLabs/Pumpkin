package net.minecraft.world.inventory;

import java.util.List;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingRecipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import dev.pumpkin.shim.Unimplemented;

public class CraftingMenu extends AbstractCraftingMenu {

    public CraftingMenu(int containerId, Inventory inventory) {
    }

    public CraftingMenu(int containerId, Inventory inventory, ContainerLevelAccess access) {
    }

    public void slotsChanged(Container container) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/CraftingMenu.slotsChanged:(Lnet/minecraft/world/Container;)V");
    }

    public void beginPlacingRecipe() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/CraftingMenu.beginPlacingRecipe:()V");
    }

    public void finishPlacingRecipe(ServerLevel level, RecipeHolder<CraftingRecipe> recipe) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/CraftingMenu.finishPlacingRecipe:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/item/crafting/RecipeHolder;)V");
    }

    public void removed(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/CraftingMenu.removed:(Lnet/minecraft/world/entity/player/Player;)V");
    }

    public boolean stillValid(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/CraftingMenu.stillValid:(Lnet/minecraft/world/entity/player/Player;)Z");
    }

    public ItemStack quickMoveStack(Player player, int slotIndex) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/CraftingMenu.quickMoveStack:(Lnet/minecraft/world/entity/player/Player;I)Lnet/minecraft/world/item/ItemStack;");
    }

    public boolean canTakeItemForPickAll(ItemStack carried, Slot target) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/CraftingMenu.canTakeItemForPickAll:(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/inventory/Slot;)Z");
    }

    public Slot getResultSlot() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/CraftingMenu.getResultSlot:()Lnet/minecraft/world/inventory/Slot;");
    }

    public List<Slot> getInputGridSlots() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/CraftingMenu.getInputGridSlots:()Ljava/util/List;");
    }

    public RecipeBookType getRecipeBookType() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/CraftingMenu.getRecipeBookType:()Lnet/minecraft/world/inventory/RecipeBookType;");
    }

    protected Player owner() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/CraftingMenu.owner:()Lnet/minecraft/world/entity/player/Player;");
    }

    public CraftingMenu() {
    }
}
