package net.neoforged.neoforge.common.crafting;

import java.util.List;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import dev.pumpkin.shim.Unimplemented;

public record CompoundIngredient(List<Ingredient> children) implements ICustomIngredient {

    public Stream<Holder<Item>> items() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/CompoundIngredient.items:()Ljava/util/stream/Stream;");
    }

    public boolean test(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/CompoundIngredient.test:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public boolean isSimple() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/CompoundIngredient.isSimple:()Z");
    }

    public IngredientType<?> getType() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/CompoundIngredient.getType:()Lnet/neoforged/neoforge/common/crafting/IngredientType;");
    }

    public SlotDisplay display() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/CompoundIngredient.display:()Lnet/minecraft/world/item/crafting/display/SlotDisplay;");
    }
}
