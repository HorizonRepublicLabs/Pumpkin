package net.neoforged.neoforge.fluids;

import java.util.function.Predicate;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponentMap;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.component.PatchedDataComponentMap;
import net.minecraft.core.component.TypedDataComponent;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.common.MutableDataComponentHolder;
import dev.pumpkin.shim.Unimplemented;

public final class FluidStack implements MutableDataComponentHolder, FluidInstance {

    public DataComponentMap getComponents() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.getComponents:()Lnet/minecraft/core/component/DataComponentMap;");
    }

    public DataComponentPatch getComponentsPatch() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.getComponentsPatch:()Lnet/minecraft/core/component/DataComponentPatch;");
    }

    public boolean isComponentsPatchEmpty() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.isComponentsPatchEmpty:()Z");
    }

    public FluidStack(Fluid fluid, int amount, DataComponentPatch patch) {
    }

    public FluidStack(Fluid fluid, int amount) {
    }

    public FluidStack(Holder<Fluid> fluid, int amount) {
    }

    public FluidStack(Holder<Fluid> fluid, int amount, DataComponentPatch patch) {
    }

    private FluidStack(Holder<Fluid> fluid, int amount, PatchedDataComponentMap components) {
    }

    private FluidStack(Void unused) {
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.isEmpty:()Z");
    }

    public Fluid getFluid() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.getFluid:()Lnet/minecraft/world/level/material/Fluid;");
    }

    public Holder<Fluid> typeHolder() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.typeHolder:()Lnet/minecraft/core/Holder;");
    }

    public boolean is(Predicate<Holder<Fluid>> holderPredicate) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.is:(Ljava/util/function/Predicate;)Z");
    }

    public FluidStack copy() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.copy:()Lnet/neoforged/neoforge/fluids/FluidStack;");
    }

    public static boolean matches(FluidStack first, FluidStack second) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.matches:(Lnet/neoforged/neoforge/fluids/FluidStack;Lnet/neoforged/neoforge/fluids/FluidStack;)Z");
    }

    public static boolean matches(FluidStack a, FluidStackTemplate b) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.matches:(Lnet/neoforged/neoforge/fluids/FluidStack;Lnet/neoforged/neoforge/fluids/FluidStackTemplate;)Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.toString:()Ljava/lang/String;");
    }

    public <T> T set(DataComponentType<T> type, T component) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.set:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Ljava/lang/Object;");
    }

    public <T> T set(TypedDataComponent<T> value) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.set:(Lnet/minecraft/core/component/TypedDataComponent;)Ljava/lang/Object;");
    }

    public <T> T remove(DataComponentType<? extends T> type) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.remove:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");
    }

    public void applyComponents(DataComponentPatch patch) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.applyComponents:(Lnet/minecraft/core/component/DataComponentPatch;)V");
    }

    public void applyComponents(DataComponentMap components) {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.applyComponents:(Lnet/minecraft/core/component/DataComponentMap;)V");
    }

    public int amount() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.amount:()I");
    }

    public int getAmount() {
        throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidStack.getAmount:()I");
    }

    public FluidStack() {
    }
}
