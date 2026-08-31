package net.minecraft.world.level.material;

import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class Fluids {


    // Pumpkin divergence: a real inert stand-in; see Fluid.pumpkinInert.
    public static final Fluid EMPTY = Fluid.pumpkinInert("empty");

    public static final FlowingFluid WATER = null;

    public static final FlowingFluid LAVA = null;

    private static <T extends Fluid> T register(ResourceKey<Fluid> id, T fluid) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/Fluids.register:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/material/Fluid;)Lnet/minecraft/world/level/material/Fluid;");
    }

    public Fluids() {
    }

    // Pumpkin divergence: no throwing initializer; WATER and LAVA stay null and any
    // read of them will say so by NPE site -- flowing fluids are a wider surface.
}
