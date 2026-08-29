package net.neoforged.neoforge.common.extensions;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.conditions.ICondition;

public interface IRecipeOutputExtension {

    void accept(ResourceKey<Recipe<?>> key, Recipe<?> recipe, AdvancementHolder advancement, ICondition... conditions);
}
