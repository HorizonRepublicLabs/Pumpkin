package net.neoforged.neoforge.common.crafting;

import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface ICustomIngredient {

    boolean test(ItemStack stack);

    Stream<Holder<Item>> items();

    boolean isSimple();

    IngredientType<?> getType();
}
