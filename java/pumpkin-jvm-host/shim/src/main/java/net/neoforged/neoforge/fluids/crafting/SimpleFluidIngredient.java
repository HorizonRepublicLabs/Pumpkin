package net.neoforged.neoforge.fluids.crafting;

import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import dev.pumpkin.shim.Unimplemented;

public class SimpleFluidIngredient extends FluidIngredient {

    public SimpleFluidIngredient(HolderSet<Fluid> values) {
    }

    public boolean test(FluidStack fluidStack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SimpleFluidIngredient.test:(Lnet/neoforged/neoforge/fluids/FluidStack;)Z");
    }

    protected Stream<Holder<Fluid>> generateFluids() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SimpleFluidIngredient.generateFluids:()Ljava/util/stream/Stream;");
    }

    public boolean isSimple() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SimpleFluidIngredient.isSimple:()Z");
    }

    public FluidIngredientType<?> getType() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SimpleFluidIngredient.getType:()Lnet/neoforged/neoforge/fluids/crafting/FluidIngredientType;");
    }

    public SlotDisplay display() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SimpleFluidIngredient.display:()Lnet/minecraft/world/item/crafting/display/SlotDisplay;");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SimpleFluidIngredient.hashCode:()I");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SimpleFluidIngredient.equals:(Ljava/lang/Object;)Z");
    }

    public HolderSet<Fluid> fluidSet() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SimpleFluidIngredient.fluidSet:()Lnet/minecraft/core/HolderSet;");
    }

    public SimpleFluidIngredient() {
    }
}
