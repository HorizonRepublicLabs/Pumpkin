package net.neoforged.neoforge.common;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import dev.pumpkin.shim.Stubs;

public class NeoForgeMod {

    public static final Holder<Attribute> CREATIVE_FLIGHT = Stubs.of(Holder.class, "net/minecraft/core/Holder");

    public NeoForgeMod(IEventBus modEventBus, Dist dist, ModContainer container) {
    }

    public NeoForgeMod() {
    }
}
