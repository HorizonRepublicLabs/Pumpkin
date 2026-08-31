package net.minecraft.world.level.material;

import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class Fluids {

    public static final Fluid EMPTY = null;

    public static final FlowingFluid WATER = null;

    public static final FlowingFluid LAVA = null;

    private static <T extends Fluid> T register(ResourceKey<Fluid> id, T fluid) {
        throw Unimplemented.forMember("net/minecraft/world/level/material/Fluids.register:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/material/Fluid;)Lnet/minecraft/world/level/material/Fluid;");
    }

    public Fluids() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/world/level/material/Fluids");
        }
    }
}
