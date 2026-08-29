package net.minecraft.world.item.crafting;

import it.unimi.dsi.fastutil.ints.IntList;
import java.util.List;
import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public class PlacementInfo {

    public static final PlacementInfo NOT_PLACEABLE = null;

    private PlacementInfo(List<Ingredient> ingredients, IntList slotsToIngredientIndex) {
    }

    public static PlacementInfo create(Ingredient ingredient) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/PlacementInfo.create:(Lnet/minecraft/world/item/crafting/Ingredient;)Lnet/minecraft/world/item/crafting/PlacementInfo;");
    }

    public static PlacementInfo createFromOptionals(List<Optional<Ingredient>> ingredients) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/PlacementInfo.createFromOptionals:(Ljava/util/List;)Lnet/minecraft/world/item/crafting/PlacementInfo;");
    }

    public static PlacementInfo create(List<Ingredient> ingredients) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/PlacementInfo.create:(Ljava/util/List;)Lnet/minecraft/world/item/crafting/PlacementInfo;");
    }

    public PlacementInfo() {
    }
}
