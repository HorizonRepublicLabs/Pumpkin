package net.minecraft.world.item.crafting;

import dev.pumpkin.shim.Unimplemented;

public abstract class CustomRecipe implements CraftingRecipe {

    public boolean isSpecial() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CustomRecipe.isSpecial:()Z");
    }

    public boolean showNotification() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CustomRecipe.showNotification:()Z");
    }

    public String group() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CustomRecipe.group:()Ljava/lang/String;");
    }

    public CraftingBookCategory category() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CustomRecipe.category:()Lnet/minecraft/world/item/crafting/CraftingBookCategory;");
    }

    public PlacementInfo placementInfo() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CustomRecipe.placementInfo:()Lnet/minecraft/world/item/crafting/PlacementInfo;");
    }

    public abstract RecipeSerializer<? extends CustomRecipe> getSerializer();

    public CustomRecipe() {
    }
}
