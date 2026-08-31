package net.neoforged.neoforge.fluids.crafting;

import java.util.List;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import dev.pumpkin.shim.Unimplemented;

public final class CompoundFluidIngredient extends FluidIngredient {

    private final List<FluidIngredient> children = null;

    public CompoundFluidIngredient(List<? extends FluidIngredient> children) {
    }

    public Stream<Holder<Fluid>> generateFluids() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/CompoundFluidIngredient.generateFluids:()Ljava/util/stream/Stream;");
    }

    public boolean test(FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/CompoundFluidIngredient.test:(Lnet/neoforged/neoforge/fluids/FluidStack;)Z");
    }

    public boolean isSimple() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/CompoundFluidIngredient.isSimple:()Z");
    }

    public FluidIngredientType<?> getType() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/CompoundFluidIngredient.getType:()Lnet/neoforged/neoforge/fluids/crafting/FluidIngredientType;");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/CompoundFluidIngredient.hashCode:()I");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/CompoundFluidIngredient.equals:(Ljava/lang/Object;)Z");
    }

    public List<FluidIngredient> children() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/CompoundFluidIngredient.children:()Ljava/util/List;");
    }

    public CompoundFluidIngredient() {
    }
}
