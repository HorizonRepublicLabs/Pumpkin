package net.minecraft.world.inventory;

import java.util.List;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.StackedItemContents;
import net.minecraft.world.item.crafting.RecipeHolder;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractCraftingMenu extends RecipeBookMenu {

    public AbstractCraftingMenu(MenuType<?> menuType, int containerId, int width, int height) {
    }

    public RecipeBookMenu.PostPlaceAction handlePlacement(boolean useMaxItems, boolean allowDroppingItemsToClear, RecipeHolder<?> recipe, ServerLevel level, Inventory inventory) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractCraftingMenu.handlePlacement:(ZZLnet/minecraft/world/item/crafting/RecipeHolder;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/player/Inventory;)Lnet/minecraft/world/inventory/RecipeBookMenu$PostPlaceAction;");
    }

    public abstract Slot getResultSlot();

    public abstract List<Slot> getInputGridSlots();

    protected abstract Player owner();

    public void fillCraftSlotsStackedContents(StackedItemContents stackedContents) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractCraftingMenu.fillCraftSlotsStackedContents:(Lnet/minecraft/world/entity/player/StackedItemContents;)V");
    }

    public AbstractCraftingMenu() {
    }
}
