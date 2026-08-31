package net.neoforged.neoforge.transfer.fluid;

import com.mojang.serialization.Codec;
import java.util.function.Predicate;
import java.util.function.Supplier;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidStackTemplate;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.resource.DataComponentHolderResource;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public final class FluidResource implements DataComponentHolderResource<Fluid> {

    // Pumpkin divergence: a real empty instance -- mods null-check it at class-init --
    // and an inert codec that throws its name on first use.
    public static final FluidResource EMPTY = new FluidResource();

    public static final Codec<FluidResource> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net/neoforged/neoforge/transfer/fluid/FluidResource.CODEC");

    public static final StreamCodec<RegistryFriendlyByteBuf, FluidResource> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    public static FluidResource of(FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.of:(Lnet/neoforged/neoforge/fluids/FluidStack;)Lnet/neoforged/neoforge/transfer/fluid/FluidResource;");
    }

    public static FluidResource of(FluidStackTemplate template) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.of:(Lnet/neoforged/neoforge/fluids/FluidStackTemplate;)Lnet/neoforged/neoforge/transfer/fluid/FluidResource;");
    }

    public static FluidResource of(Fluid fluid) {
        FluidResource resource = new FluidResource();
        resource.pumpkinFluid = fluid;
        return resource;
    }

    public static FluidResource of(Fluid fluid, DataComponentPatch patch) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.of:(Lnet/minecraft/world/level/material/Fluid;Lnet/minecraft/core/component/DataComponentPatch;)Lnet/neoforged/neoforge/transfer/fluid/FluidResource;");
    }

    public static FluidResource of(Holder<Fluid> fluid) {
        return of(fluid.value());
    }

    public static FluidResource of(Holder<Fluid> holder, DataComponentPatch patch) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.of:(Lnet/minecraft/core/Holder;Lnet/minecraft/core/component/DataComponentPatch;)Lnet/neoforged/neoforge/transfer/fluid/FluidResource;");
    }

    private FluidResource(FluidStack stack) {
    }

    public Fluid value() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.value:()Lnet/minecraft/world/level/material/Fluid;");
    }

    public Fluid getFluid() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.getFluid:()Lnet/minecraft/world/level/material/Fluid;");
    }

    public Holder<Fluid> typeHolder() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.typeHolder:()Lnet/minecraft/core/Holder;");
    }

    public FluidType getFluidType() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.getFluidType:()Lnet/neoforged/neoforge/fluids/FluidType;");
    }

    // Pumpkin divergence: real body -- the shared EMPTY instance is the empty one; a
    // resource built by an of() overload will carry its fluid when those get bodies.
    private Fluid pumpkinFluid;

    public boolean isEmpty() {
        return pumpkinFluid == null;
    }

    public FluidResource withMergedPatch(DataComponentPatch patch) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.withMergedPatch:(Lnet/minecraft/core/component/DataComponentPatch;)Lnet/neoforged/neoforge/transfer/fluid/FluidResource;");
    }

    public <D> FluidResource with(DataComponentType<D> type, D data) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.with:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/neoforged/neoforge/transfer/fluid/FluidResource;");
    }

    public <D> FluidResource with(Supplier<? extends DataComponentType<D>> type, D data) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.with:(Ljava/util/function/Supplier;Ljava/lang/Object;)Lnet/neoforged/neoforge/transfer/fluid/FluidResource;");
    }

    public FluidResource without(DataComponentType<?> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.without:(Lnet/minecraft/core/component/DataComponentType;)Lnet/neoforged/neoforge/transfer/fluid/FluidResource;");
    }

    public FluidResource without(Supplier<? extends DataComponentType<?>> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.without:(Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/transfer/fluid/FluidResource;");
    }

    public DataComponentMap getComponents() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.getComponents:()Lnet/minecraft/core/component/DataComponentMap;");
    }

    public DataComponentPatch getComponentsPatch() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.getComponentsPatch:()Lnet/minecraft/core/component/DataComponentPatch;");
    }

    public FluidStack toStack(int amount) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.toStack:(I)Lnet/neoforged/neoforge/fluids/FluidStack;");
    }

    public boolean isComponentsPatchEmpty() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.isComponentsPatchEmpty:()Z");
    }

    public boolean is(FluidType fluidType) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.is:(Lnet/neoforged/neoforge/fluids/FluidType;)Z");
    }

    public boolean matches(FluidStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.matches:(Lnet/neoforged/neoforge/fluids/FluidStack;)Z");
    }

    public boolean matches(FluidStackTemplate template) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.matches:(Lnet/neoforged/neoforge/fluids/FluidStackTemplate;)Z");
    }

    public boolean test(Predicate<FluidStack> predicate) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.test:(Ljava/util/function/Predicate;)Z");
    }

    public Component getHoverName() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.getHoverName:()Lnet/minecraft/network/chat/Component;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.toString:()Ljava/lang/String;");
    }

    public FluidResource() {
    }
}
