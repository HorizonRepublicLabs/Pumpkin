package net.minecraft.world.item.crafting.display;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeBookCategory;

public record RecipeDisplayEntry(RecipeDisplayId id, RecipeDisplay display, OptionalInt group, RecipeBookCategory category, Optional<List<Ingredient>> craftingRequirements) {
}
