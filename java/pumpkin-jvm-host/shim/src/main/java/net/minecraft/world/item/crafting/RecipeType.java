package net.minecraft.world.item.crafting;

import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public interface RecipeType<T extends Recipe<?>> {

    RecipeType<SmeltingRecipe> SMELTING = null;

    static <T extends Recipe<?>> RecipeType<T> register(String name) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeType.register:(Ljava/lang/String;)Lnet/minecraft/world/item/crafting/RecipeType;");
    }

    public static <T extends Recipe<?>> RecipeType<T> simple(final Identifier name) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeType.simple:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/world/item/crafting/RecipeType;");
    }
}
