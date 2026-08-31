package net.neoforged.neoforge.common.crafting;

import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.ItemLike;
import dev.pumpkin.shim.Unimplemented;

public class DataComponentIngredient implements ICustomIngredient {

    private final DataComponentPatch components = null;

    public DataComponentIngredient(HolderSet<Item> items, DataComponentPatch components, boolean exhaustive) {
    }

    public boolean test(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.test:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public Stream<Holder<Item>> items() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.items:()Ljava/util/stream/Stream;");
    }

    public boolean isSimple() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.isSimple:()Z");
    }

    public IngredientType<?> getType() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.getType:()Lnet/neoforged/neoforge/common/crafting/IngredientType;");
    }

    public SlotDisplay display() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.display:()Lnet/minecraft/world/item/crafting/display/SlotDisplay;");
    }

    public HolderSet<Item> itemSet() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.itemSet:()Lnet/minecraft/core/HolderSet;");
    }

    public DataComponentPatch components() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.components:()Lnet/minecraft/core/component/DataComponentPatch;");
    }

    public boolean componentsExhaustive() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.componentsExhaustive:()Z");
    }

    public static Ingredient of(boolean exhaustive, ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.of:(ZLnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public static Ingredient of(boolean exhaustive, ItemStackTemplate stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.of:(ZLnet/minecraft/world/item/ItemStackTemplate;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public static <T> Ingredient of(DataComponentType<? super T> type, T value, ItemLike... items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.of:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;[Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public static <T> Ingredient of(boolean exhaustive, DataComponentType<? super T> type, T value, ItemLike... items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.of:(ZLnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;[Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public static <T> Ingredient of(boolean exhaustive, Supplier<? extends DataComponentType<? super T>> type, T value, ItemLike... items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.of:(ZLjava/util/function/Supplier;Ljava/lang/Object;[Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public static Ingredient of(boolean exhaustive, DataComponentMap map, ItemLike... items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.of:(ZLnet/minecraft/core/component/DataComponentMap;[Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public static Ingredient of(boolean exhaustive, DataComponentMap map, Holder<Item>... items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.of:(ZLnet/minecraft/core/component/DataComponentMap;[Lnet/minecraft/core/Holder;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public static Ingredient of(boolean exhaustive, DataComponentMap map, HolderSet<Item> items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.of:(ZLnet/minecraft/core/component/DataComponentMap;Lnet/minecraft/core/HolderSet;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public static Ingredient of(boolean exhaustive, DataComponentPatch predicate, Holder<Item>... items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.of:(ZLnet/minecraft/core/component/DataComponentPatch;[Lnet/minecraft/core/Holder;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public static Ingredient of(DataComponentPatch predicate, ItemLike... items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.of:(Lnet/minecraft/core/component/DataComponentPatch;[Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public static Ingredient of(boolean exhaustive, DataComponentPatch predicate, ItemLike... items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.of:(ZLnet/minecraft/core/component/DataComponentPatch;[Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public static Ingredient of(boolean exhaustive, DataComponentPatch predicate, HolderSet<Item> items) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/DataComponentIngredient.of:(ZLnet/minecraft/core/component/DataComponentPatch;Lnet/minecraft/core/HolderSet;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public DataComponentIngredient() {
    }
}
