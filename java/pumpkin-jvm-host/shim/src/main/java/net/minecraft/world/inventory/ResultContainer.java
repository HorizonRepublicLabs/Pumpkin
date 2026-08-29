package net.minecraft.world.inventory;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import dev.pumpkin.shim.Unimplemented;

public class ResultContainer implements Container, RecipeCraftingHolder {

    public int getContainerSize() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ResultContainer.getContainerSize:()I");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ResultContainer.isEmpty:()Z");
    }

    public ItemStack getItem(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ResultContainer.getItem:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack removeItem(int slot, int count) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ResultContainer.removeItem:(II)Lnet/minecraft/world/item/ItemStack;");
    }

    public ItemStack removeItemNoUpdate(int slot) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ResultContainer.removeItemNoUpdate:(I)Lnet/minecraft/world/item/ItemStack;");
    }

    public void setItem(int slot, ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ResultContainer.setItem:(ILnet/minecraft/world/item/ItemStack;)V");
    }

    public void setChanged() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ResultContainer.setChanged:()V");
    }

    public boolean stillValid(Player player) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ResultContainer.stillValid:(Lnet/minecraft/world/entity/player/Player;)Z");
    }

    public void clearContent() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ResultContainer.clearContent:()V");
    }

    public void setRecipeUsed(RecipeHolder<?> recipeUsed) {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ResultContainer.setRecipeUsed:(Lnet/minecraft/world/item/crafting/RecipeHolder;)V");
    }

    public RecipeHolder<?> getRecipeUsed() {
        throw Unimplemented.forMember("net/minecraft/world/inventory/ResultContainer.getRecipeUsed:()Lnet/minecraft/world/item/crafting/RecipeHolder;");
    }

    public ResultContainer() {
    }
}
