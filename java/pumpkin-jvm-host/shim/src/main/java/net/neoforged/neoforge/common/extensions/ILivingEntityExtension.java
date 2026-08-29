package net.neoforged.neoforge.common.extensions;

import net.neoforged.neoforge.fluids.FluidType;
import dev.pumpkin.shim.Unimplemented;

public interface ILivingEntityExtension extends IEntityExtension {

    default boolean canSwimInFluidType(FluidType type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ILivingEntityExtension.canSwimInFluidType:(Lnet/neoforged/neoforge/fluids/FluidType;)Z");
    }
}
