package net.minecraft.world.item.crafting;

import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public interface RecipeType<T extends Recipe<?>> {

    // Pumpkin divergence: the vanilla constants really are simple() tokens whose
    // toString is their id -- that is vanilla's own shape.
    RecipeType<CraftingRecipe> CRAFTING = simple(Identifier.parse("minecraft:crafting"));

    RecipeType<SmeltingRecipe> SMELTING = simple(Identifier.parse("minecraft:smelting"));

    static <T extends Recipe<?>> RecipeType<T> register(String name) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeType.register:(Ljava/lang/String;)Lnet/minecraft/world/item/crafting/RecipeType;");
    }

    // Pumpkin divergence: vanilla body verbatim -- fully self-contained, just a token
    // whose toString is its id.
    public static <T extends Recipe<?>> RecipeType<T> simple(final Identifier name) {
        return new RecipeType<T>() {
            @Override
            public String toString() {
                return name.toString();
            }
        };
    }
}
