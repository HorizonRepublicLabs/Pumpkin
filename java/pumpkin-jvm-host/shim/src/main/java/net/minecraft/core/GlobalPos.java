package net.minecraft.core;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public record GlobalPos(ResourceKey<Level> dimension, BlockPos pos) {

    public static final Codec<GlobalPos> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net.minecraft.core.GlobalPos.CODEC");

    // Pumpkin divergence: real bodies. The record already carries both components;
    // vanilla's factory and printed form are exactly this.
    public static GlobalPos of(ResourceKey<Level> dimension, BlockPos pos) {
        return new GlobalPos(dimension, pos);
    }

    public String toString() {
        return this.dimension + " " + this.pos;
    }
}
