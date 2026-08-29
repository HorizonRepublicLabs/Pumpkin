package net.neoforged.neoforge.fluids;

import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.level.material.Fluid;
import dev.pumpkin.shim.Unimplemented;

public record FluidStackTemplate(Holder<Fluid> fluid, int amount, DataComponentPatch components) implements FluidInstance {

    public FluidStackTemplate(Holder<Fluid> fluid, int amount) {
        this((Holder<Fluid>) null, (int) 0, (DataComponentPatch) null);
    }

    public FluidStackTemplate(Fluid fluid, int amount, DataComponentPatch components) {
        this((Holder<Fluid>) null, (int) 0, (DataComponentPatch) null);
    }

    public FluidStackTemplate(Fluid fluid, int amount) {
        this((Holder<Fluid>) null, (int) 0, (DataComponentPatch) null);
    }

    public FluidStack create() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStackTemplate.create:()Lnet/neoforged/neoforge/fluids/FluidStack;");
    }

    public FluidStack apply(DataComponentPatch additionalPatch) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStackTemplate.apply:(Lnet/minecraft/core/component/DataComponentPatch;)Lnet/neoforged/neoforge/fluids/FluidStack;");
    }

    public FluidStack apply(int amount, DataComponentPatch additionalPatch) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStackTemplate.apply:(ILnet/minecraft/core/component/DataComponentPatch;)Lnet/neoforged/neoforge/fluids/FluidStack;");
    }

    public Holder<Fluid> typeHolder() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStackTemplate.typeHolder:()Lnet/minecraft/core/Holder;");
    }

    public <T> T get(DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStackTemplate.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }
}
