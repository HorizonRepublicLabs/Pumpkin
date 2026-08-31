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

    // Pumpkin divergence: real body -- the flag flips, as in NeoForge. The MILK holder
    // itself stays null until something actually reads it, and that read will say so.
    public static void enableMilkFluid() {
        enableMilkFluid = true;
    }

    public NeoForgeMod(IEventBus modEventBus, Dist dist, ModContainer container) {
    }

    public NeoForgeMod() {
    }
}
