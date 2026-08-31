package net.minecraft.world.item.crafting;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class SmeltingRecipe extends AbstractCookingRecipe {

    public static final MapCodec<SmeltingRecipe> MAP_CODEC = null;

    public static final StreamCodec<RegistryFriendlyByteBuf, SmeltingRecipe> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    public SmeltingRecipe(Recipe.CommonInfo commonInfo, AbstractCookingRecipe.CookingBookInfo bookInfo, Ingredient ingredient, ItemStackTemplate result, float experience, int cookingTime) {
    }

    protected Item furnaceIcon() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SmeltingRecipe.furnaceIcon:()Lnet/minecraft/world/item/Item;");
    }

    public RecipeSerializer<SmeltingRecipe> getSerializer() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SmeltingRecipe.getSerializer:()Lnet/minecraft/world/item/crafting/RecipeSerializer;");
    }

    public RecipeType<SmeltingRecipe> getType() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SmeltingRecipe.getType:()Lnet/minecraft/world/item/crafting/RecipeType;");
    }

    public RecipeBookCategory recipeBookCategory() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SmeltingRecipe.recipeBookCategory:()Lnet/minecraft/world/item/crafting/RecipeBookCategory;");
    }

    public SmeltingRecipe() {
    }
}
