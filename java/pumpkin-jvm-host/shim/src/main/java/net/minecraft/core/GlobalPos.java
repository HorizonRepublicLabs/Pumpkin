package net.minecraft.core;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public record GlobalPos(ResourceKey<Level> dimension, BlockPos pos) {

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/core/GlobalPos.toString:()Ljava/lang/String;");
    }
}
