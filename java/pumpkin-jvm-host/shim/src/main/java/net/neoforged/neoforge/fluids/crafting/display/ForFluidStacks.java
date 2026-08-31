package net.neoforged.neoforge.fluids.crafting.display;

import net.minecraft.core.Holder;
import net.minecraft.world.item.crafting.display.DisplayContentsFactory;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import dev.pumpkin.shim.Unimplemented;

public interface ForFluidStacks<T> extends DisplayContentsFactory<T> {

    default T forStack(Holder<Fluid> fluid) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/display/ForFluidStacks.forStack:(Lnet/minecraft/core/Holder;)Ljava/lang/Object;");
    }

    default T forStack(Fluid fluid) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/display/ForFluidStacks.forStack:(Lnet/minecraft/world/level/material/Fluid;)Ljava/lang/Object;");
    }

    T forStack(FluidStack fluid);
}
