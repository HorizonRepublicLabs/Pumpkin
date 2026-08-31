package net.neoforged.neoforge.fluids.crafting;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public final class SizedFluidIngredient {

    public static final Codec<SizedFluidIngredient> CODEC = null;

    public static final StreamCodec<RegistryFriendlyByteBuf, SizedFluidIngredient> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    public static SizedFluidIngredient of(Fluid fluid, int amount) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SizedFluidIngredient.of:(Lnet/minecraft/world/level/material/Fluid;I)Lnet/neoforged/neoforge/fluids/crafting/SizedFluidIngredient;");
    }

    private final FluidIngredient ingredient = null;

    private final int amount = 0;

    public SizedFluidIngredient(FluidIngredient ingredient, int amount) {
    }

    public FluidIngredient ingredient() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SizedFluidIngredient.ingredient:()Lnet/neoforged/neoforge/fluids/crafting/FluidIngredient;");
    }

    public int amount() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SizedFluidIngredient.amount:()I");
    }

    public boolean test(FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SizedFluidIngredient.test:(Lnet/neoforged/neoforge/fluids/FluidStack;)Z");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SizedFluidIngredient.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SizedFluidIngredient.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/crafting/SizedFluidIngredient.toString:()Ljava/lang/String;");
    }

    public SizedFluidIngredient() {
    }
}
