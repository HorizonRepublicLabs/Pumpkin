package net.neoforged.neoforge.fluids.crafting;

import java.util.function.Supplier;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentExactPredicate;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import dev.pumpkin.shim.Unimplemented;

public class DataComponentFluidIngredient extends FluidIngredient {

    private final DataComponentExactPredicate components = null;

    public DataComponentFluidIngredient(HolderSet<Fluid> fluids, DataComponentExactPredicate components, boolean strict) {
    }

    public boolean test(FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.test:(Lnet/neoforged/neoforge/fluids/FluidStack;)Z");
    }

    public Stream<Holder<Fluid>> generateFluids() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.generateFluids:()Ljava/util/stream/Stream;");
    }

    public SlotDisplay display() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.display:()Lnet/minecraft/world/item/crafting/display/SlotDisplay;");
    }

    public boolean isSimple() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.isSimple:()Z");
    }

    public FluidIngredientType<?> getType() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.getType:()Lnet/neoforged/neoforge/fluids/crafting/FluidIngredientType;");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.hashCode:()I");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.equals:(Ljava/lang/Object;)Z");
    }

    public HolderSet<Fluid> fluidSet() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.fluidSet:()Lnet/minecraft/core/HolderSet;");
    }

    public DataComponentExactPredicate components() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.components:()Lnet/minecraft/core/component/DataComponentExactPredicate;");
    }

    public boolean isStrict() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.isStrict:()Z");
    }

    public static FluidIngredient of(boolean strict, FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.of:(ZLnet/neoforged/neoforge/fluids/FluidStack;)Lnet/neoforged/neoforge/fluids/crafting/FluidIngredient;");
    }

    public static <T> FluidIngredient of(boolean strict, DataComponentType<? super T> type, T value, Fluid... fluids) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.of:(ZLnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;[Lnet/minecraft/world/level/material/Fluid;)Lnet/neoforged/neoforge/fluids/crafting/FluidIngredient;");
    }

    public static <T> FluidIngredient of(boolean strict, Supplier<? extends DataComponentType<? super T>> type, T value, Fluid... fluids) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.of:(ZLjava/util/function/Supplier;Ljava/lang/Object;[Lnet/minecraft/world/level/material/Fluid;)Lnet/neoforged/neoforge/fluids/crafting/FluidIngredient;");
    }

    public static FluidIngredient of(boolean strict, DataComponentMap map, Fluid... fluids) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.of:(ZLnet/minecraft/core/component/DataComponentMap;[Lnet/minecraft/world/level/material/Fluid;)Lnet/neoforged/neoforge/fluids/crafting/FluidIngredient;");
    }

    public static FluidIngredient of(boolean strict, DataComponentMap map, Holder<Fluid>... fluids) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.of:(ZLnet/minecraft/core/component/DataComponentMap;[Lnet/minecraft/core/Holder;)Lnet/neoforged/neoforge/fluids/crafting/FluidIngredient;");
    }

    public static FluidIngredient of(boolean strict, DataComponentMap map, HolderSet<Fluid> fluids) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.of:(ZLnet/minecraft/core/component/DataComponentMap;Lnet/minecraft/core/HolderSet;)Lnet/neoforged/neoforge/fluids/crafting/FluidIngredient;");
    }

    public static FluidIngredient of(boolean strict, DataComponentExactPredicate predicate, Holder<Fluid>... fluids) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.of:(ZLnet/minecraft/core/component/DataComponentExactPredicate;[Lnet/minecraft/core/Holder;)Lnet/neoforged/neoforge/fluids/crafting/FluidIngredient;");
    }

    public static FluidIngredient of(boolean strict, DataComponentExactPredicate predicate, Fluid... fluids) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.of:(ZLnet/minecraft/core/component/DataComponentExactPredicate;[Lnet/minecraft/world/level/material/Fluid;)Lnet/neoforged/neoforge/fluids/crafting/FluidIngredient;");
    }

    public static FluidIngredient of(boolean strict, DataComponentExactPredicate predicate, HolderSet<Fluid> fluids) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/DataComponentFluidIngredient.of:(ZLnet/minecraft/core/component/DataComponentExactPredicate;Lnet/minecraft/core/HolderSet;)Lnet/neoforged/neoforge/fluids/crafting/FluidIngredient;");
    }

    public DataComponentFluidIngredient() {
    }
}
