package net.minecraft.data.recipes;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.extensions.IRecipeOutputExtension;
import dev.pumpkin.shim.Unimplemented;

public interface RecipeOutput extends IRecipeOutputExtension {

    default void accept(ResourceKey<Recipe<?>> id, Recipe<?> recipe, AdvancementHolder advancement) {
        throw Unimplemented.forMember("net/minecraft/data/recipes/RecipeOutput.accept:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/item/crafting/Recipe;Lnet/minecraft/advancements/AdvancementHolder;)V");
    }

    Advancement.Builder advancement();

    void includeRootAdvancement();
}
