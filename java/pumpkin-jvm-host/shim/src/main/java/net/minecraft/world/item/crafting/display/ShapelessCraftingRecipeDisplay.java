package net.minecraft.world.item.crafting.display;

import java.util.List;
import net.minecraft.world.flag.FeatureFlagSet;
import dev.pumpkin.shim.Unimplemented;

public record ShapelessCraftingRecipeDisplay(List<SlotDisplay> ingredients, SlotDisplay result, SlotDisplay craftingStation) implements RecipeDisplay {

    public RecipeDisplay.Type<ShapelessCraftingRecipeDisplay> type() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/ShapelessCraftingRecipeDisplay.type:()Lnet/minecraft/world/item/crafting/display/RecipeDisplay$Type;");
    }

    public boolean isEnabled(FeatureFlagSet enabledFeatures) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/ShapelessCraftingRecipeDisplay.isEnabled:(Lnet/minecraft/world/flag/FeatureFlagSet;)Z");
    }
}
