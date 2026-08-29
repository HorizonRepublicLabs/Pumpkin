package net.neoforged.neoforge.common.extensions;

import net.neoforged.neoforge.fluids.FluidType;
import dev.pumpkin.shim.Unimplemented;

public interface IFluidStateExtension {

    default FluidType getFluidType() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IFluidStateExtension.getFluidType:()Lnet/neoforged/neoforge/fluids/FluidType;");
    }
}
