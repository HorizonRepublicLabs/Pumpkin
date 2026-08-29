package net.minecraft.world.item.crafting;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;
import dev.pumpkin.shim.Stubs;

public interface CraftingRecipe extends Recipe<CraftingInput> {

    default RecipeType<CraftingRecipe> getType() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingRecipe.getType:()Lnet/minecraft/world/item/crafting/RecipeType;");
    }

    RecipeSerializer<? extends CraftingRecipe> getSerializer();

    CraftingBookCategory category();

    static NonNullList<ItemStack> defaultCraftingReminder(CraftingInput input) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingRecipe.defaultCraftingReminder:(Lnet/minecraft/world/item/crafting/CraftingInput;)Lnet/minecraft/core/NonNullList;");
    }

    default RecipeBookCategory recipeBookCategory() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingRecipe.recipeBookCategory:()Lnet/minecraft/world/item/crafting/RecipeBookCategory;");
    }

    record CraftingBookInfo(CraftingBookCategory category, String group) implements Recipe.BookInfo<CraftingBookCategory> {

        // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while

        // composing at class-init; null there is an NPE naming nothing. This survives

        // composition and throws on first real serialisation, naming the field.

        public static final MapCodec<CraftingRecipe.CraftingBookInfo> MAP_CODEC = dev.pumpkin.shim.Stubs.throwingMapCodec("net/minecraft/world/item/crafting/CraftingRecipe.MAP_CODEC");

        public static final StreamCodec<RegistryFriendlyByteBuf, CraftingRecipe.CraftingBookInfo> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");
    }
}
