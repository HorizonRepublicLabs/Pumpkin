package net.neoforged.neoforge.fluids;

import net.minecraft.core.TypedInstance;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.world.level.material.Fluid;
import dev.pumpkin.shim.Unimplemented;

public interface FluidInstance extends TypedInstance<Fluid>, DataComponentGetter {

    int amount();

    default FluidType getFluidType() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidInstance.getFluidType:()Lnet/neoforged/neoforge/fluids/FluidType;");
    }

    default boolean is(FluidType fluidType) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidInstance.is:(Lnet/neoforged/neoforge/fluids/FluidType;)Z");
    }
}
