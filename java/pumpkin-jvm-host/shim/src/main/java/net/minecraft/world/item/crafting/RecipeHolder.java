package net.minecraft.world.item.crafting;

import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public record RecipeHolder<T extends Recipe<?>>(ResourceKey<Recipe<?>> id, T value) {

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeHolder.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeHolder.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeHolder.toString:()Ljava/lang/String;");
    }
}
