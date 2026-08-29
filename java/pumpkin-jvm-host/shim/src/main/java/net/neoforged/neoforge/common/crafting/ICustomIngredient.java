package net.neoforged.neoforge.common.crafting;

import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import dev.pumpkin.shim.Unimplemented;

public interface ICustomIngredient {

    boolean test(ItemStack stack);

    Stream<Holder<Item>> items();

    boolean isSimple();

    IngredientType<?> getType();

    default Ingredient toVanilla() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/ICustomIngredient.toVanilla:()Lnet/minecraft/world/item/crafting/Ingredient;");
    }
}
