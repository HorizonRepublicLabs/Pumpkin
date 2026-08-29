package net.minecraft.tags;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.material.Fluid;
import dev.pumpkin.shim.Unimplemented;

public final class FluidTags {

    public static final TagKey<Fluid> WATER = null;

    protected FluidTags() {
    }

    private static TagKey<Fluid> create(String name) {
        throw Unimplemented.forMember("net/minecraft/tags/FluidTags.create:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
    }

    public static TagKey<Fluid> create(Identifier name) {
        throw Unimplemented.forMember("net/minecraft/tags/FluidTags.create:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/tags/TagKey;");
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/tags/FluidTags");
        }
    }
}
