package net.neoforged.neoforge.common;

import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import dev.pumpkin.shim.Unimplemented;

public class NeoForgeMod {

    public static final Holder<Attribute> CREATIVE_FLIGHT = null;

    public NeoForgeMod(IEventBus modEventBus, Dist dist, ModContainer container) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/NeoForgeMod.<init>:(Lnet/neoforged/bus/api/IEventBus;Lnet/neoforged/api/distmarker/Dist;Lnet/neoforged/fml/ModContainer;)V");
    }

    protected NeoForgeMod() {
    }
}
