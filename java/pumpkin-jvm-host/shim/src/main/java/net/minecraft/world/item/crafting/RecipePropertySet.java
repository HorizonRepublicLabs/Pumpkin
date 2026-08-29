package net.minecraft.world.item.crafting;

import java.util.Collection;
import java.util.Set;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class RecipePropertySet {

    private RecipePropertySet(Set<Holder<Item>> items) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipePropertySet.<init>:(Ljava/util/Set;)V");
    }

    public boolean test(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipePropertySet.test:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public static RecipePropertySet create(Collection<Ingredient> ingredients) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipePropertySet.create:(Ljava/util/Collection;)Lnet/minecraft/world/item/crafting/RecipePropertySet;");
    }

    protected RecipePropertySet() {
    }
}
