package net.minecraft.world.item.crafting.display;

import java.util.List;
import net.minecraft.world.flag.FeatureFlagSet;
import dev.pumpkin.shim.Unimplemented;

public record ShapedCraftingRecipeDisplay(int width, int height, List<SlotDisplay> ingredients, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {

    public RecipeDisplay.Type<ShapedCraftingRecipeDisplay> type() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/ShapedCraftingRecipeDisplay.type:()Lnet/minecraft/world/item/crafting/display/RecipeDisplay$Type;");
    }

    public boolean isEnabled(FeatureFlagSet enabledFeatures) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/ShapedCraftingRecipeDisplay.isEnabled:(Lnet/minecraft/world/flag/FeatureFlagSet;)Z");
    }
}
