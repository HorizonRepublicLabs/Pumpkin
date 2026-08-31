package net.neoforged.neoforge.fluids;

import com.mojang.serialization.Codec;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.material.Fluid;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public record FluidStackTemplate(Holder<Fluid> fluid, int amount, DataComponentPatch components) implements FluidInstance {

    public static final Codec<FluidStackTemplate> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net.neoforged.neoforge.fluids.FluidStackTemplate.CODEC");

    public static final StreamCodec<RegistryFriendlyByteBuf, FluidStackTemplate> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

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
