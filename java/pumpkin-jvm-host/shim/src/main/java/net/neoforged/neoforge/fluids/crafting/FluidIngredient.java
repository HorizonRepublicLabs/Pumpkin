package net.neoforged.neoforge.fluids.crafting;

import com.mojang.serialization.Codec;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import dev.pumpkin.shim.Unimplemented;

public abstract class FluidIngredient implements Predicate<FluidStack> {

    public static final Codec<FluidIngredient> CODEC = null;

    public abstract boolean test(FluidStack fluidStack);

    protected abstract Stream<Holder<Fluid>> generateFluids();

    public SlotDisplay display() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/FluidIngredient.display:()Lnet/minecraft/world/item/crafting/display/SlotDisplay;");
    }

    public abstract boolean isSimple();

    public abstract FluidIngredientType<?> getType();

    public abstract int hashCode();

    public abstract boolean equals(Object obj);

    public static FluidIngredient of(FluidStack... fluids) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/FluidIngredient.of:([Lnet/neoforged/neoforge/fluids/FluidStack;)Lnet/neoforged/neoforge/fluids/crafting/FluidIngredient;");
    }

    public static FluidIngredient of(Fluid... fluids) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/FluidIngredient.of:([Lnet/minecraft/world/level/material/Fluid;)Lnet/neoforged/neoforge/fluids/crafting/FluidIngredient;");
    }

    public static FluidIngredient of(Stream<Fluid> fluids) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/FluidIngredient.of:(Ljava/util/stream/Stream;)Lnet/neoforged/neoforge/fluids/crafting/FluidIngredient;");
    }

    public static FluidIngredient of(HolderSet<Fluid> fluids) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/FluidIngredient.of:(Lnet/minecraft/core/HolderSet;)Lnet/neoforged/neoforge/fluids/crafting/FluidIngredient;");
    }

    public FluidIngredient() {
    }
}
