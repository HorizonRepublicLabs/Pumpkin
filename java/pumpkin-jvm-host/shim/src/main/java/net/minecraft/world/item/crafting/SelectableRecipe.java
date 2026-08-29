package net.minecraft.world.item.crafting;

import java.util.List;
import java.util.Optional;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import dev.pumpkin.shim.Unimplemented;

public record SelectableRecipe<T extends Recipe<?>>(SlotDisplay optionDisplay, Optional<RecipeHolder<T>> recipe) {

    public record SingleInputEntry<T extends Recipe<?>>(Ingredient input, SelectableRecipe<T> recipe) {
    }

    public record SingleInputSet<T extends Recipe<?>>(List<SelectableRecipe.SingleInputEntry<T>> entries) {

        public boolean isEmpty() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/SelectableRecipe$SingleInputSet.isEmpty:()Z");
        }

        public int size() {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/SelectableRecipe$SingleInputSet.size:()I");
        }
    }
}
