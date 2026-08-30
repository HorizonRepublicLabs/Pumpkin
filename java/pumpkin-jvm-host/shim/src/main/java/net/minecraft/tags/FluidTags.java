package net.minecraft.tags;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.material.Fluid;
import dev.pumpkin.shim.Unimplemented;

public final class FluidTags {

    // Pumpkin divergence: real value, named as vanilla names it.
    public static final TagKey<Fluid> WATER = create(Identifier.fromNamespaceAndPath("minecraft", "water"));

    protected FluidTags() {
    }

    private static TagKey<Fluid> create(String name) {
        throw Unimplemented.forMember("net/minecraft/tags/FluidTags.create:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
    }

    // Pumpkin divergence: real body.
    public static TagKey<Fluid> create(Identifier name) {
        return TagKey.create(net.minecraft.resources.ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("minecraft", "fluid")), name);
    }

}
