package net.minecraft.data.recipes;

import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import dev.pumpkin.shim.Unimplemented;

public interface RecipeBuilder {

    RecipeBuilder unlockedBy(String name, Criterion<?> criterion);

    RecipeBuilder group(String group);

    ResourceKey<Recipe<?>> defaultId();

    void save(RecipeOutput output, ResourceKey<Recipe<?>> location);

    default void save(RecipeOutput output) {
        throw Unimplemented.forMember("net/minecraft/data/recipes/RecipeBuilder.save:(Lnet/minecraft/data/recipes/RecipeOutput;)V");
    }

    default void save(RecipeOutput output, String id) {
        throw Unimplemented.forMember("net/minecraft/data/recipes/RecipeBuilder.save:(Lnet/minecraft/data/recipes/RecipeOutput;Ljava/lang/String;)V");
    }
}
