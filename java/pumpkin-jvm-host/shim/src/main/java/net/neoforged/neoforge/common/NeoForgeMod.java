package net.neoforged.neoforge.common;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public class NeoForgeMod {

    public static final Holder<Attribute> SWIM_SPEED = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<Attribute> CREATIVE_FLIGHT = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<Attribute> GLIDING_FLIGHT = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public static final Holder<FluidType> WATER_TYPE = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    private static boolean enableMilkFluid;

    public static final DeferredHolder<Fluid, Fluid> MILK = null;

    public static void enableMilkFluid() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/NeoForgeMod.enableMilkFluid:()V");
    }

    public NeoForgeMod(IEventBus modEventBus, Dist dist, ModContainer container) {
    }

    public NeoForgeMod() {
    }
}
