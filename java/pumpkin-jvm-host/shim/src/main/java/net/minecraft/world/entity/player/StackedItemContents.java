package net.minecraft.world.entity.player;

import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import dev.pumpkin.shim.Unimplemented;

public class StackedItemContents {

    public boolean canCraft(Recipe<?> recipe, StackedContents.Output<Holder<Item>> output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/StackedItemContents.canCraft:(Lnet/minecraft/world/item/crafting/Recipe;Lnet/minecraft/world/entity/player/StackedContents$Output;)Z");
    }

    public boolean canCraft(Recipe<?> recipe, int amount, StackedContents.Output<Holder<Item>> output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/StackedItemContents.canCraft:(Lnet/minecraft/world/item/crafting/Recipe;ILnet/minecraft/world/entity/player/StackedContents$Output;)Z");
    }

    public boolean canCraft(List<? extends StackedContents.IngredientInfo<Holder<Item>>> contents, StackedContents.Output<Holder<Item>> output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/StackedItemContents.canCraft:(Ljava/util/List;Lnet/minecraft/world/entity/player/StackedContents$Output;)Z");
    }

    private boolean canCraft(List<? extends StackedContents.IngredientInfo<Holder<Item>>> contents, int amount, StackedContents.Output<Holder<Item>> output) {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/StackedItemContents.canCraft:(Ljava/util/List;ILnet/minecraft/world/entity/player/StackedContents$Output;)Z");
    }

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/world/entity/player/StackedItemContents.clear:()V");
    }

    public StackedItemContents() {
    }
}
