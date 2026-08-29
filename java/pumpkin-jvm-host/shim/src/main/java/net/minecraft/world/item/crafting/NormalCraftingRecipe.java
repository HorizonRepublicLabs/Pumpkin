package net.minecraft.world.item.crafting;

import dev.pumpkin.shim.Unimplemented;

public abstract class NormalCraftingRecipe implements CraftingRecipe {

    protected final Recipe.CommonInfo commonInfo = null;

    protected final CraftingRecipe.CraftingBookInfo bookInfo = null;

    protected NormalCraftingRecipe(Recipe.CommonInfo commonInfo, CraftingRecipe.CraftingBookInfo bookInfo) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/NormalCraftingRecipe.<init>:(Lnet/minecraft/world/item/crafting/Recipe$CommonInfo;Lnet/minecraft/world/item/crafting/CraftingRecipe$CraftingBookInfo;)V");
    }

    public abstract RecipeSerializer<? extends NormalCraftingRecipe> getSerializer();

    public final String group() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/NormalCraftingRecipe.group:()Ljava/lang/String;");
    }

    public final CraftingBookCategory category() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/NormalCraftingRecipe.category:()Lnet/minecraft/world/item/crafting/CraftingBookCategory;");
    }

    public final boolean showNotification() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/NormalCraftingRecipe.showNotification:()Z");
    }

    protected abstract PlacementInfo createPlacementInfo();

    public final PlacementInfo placementInfo() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/NormalCraftingRecipe.placementInfo:()Lnet/minecraft/world/item/crafting/PlacementInfo;");
    }

    public NormalCraftingRecipe() {
    }
}
