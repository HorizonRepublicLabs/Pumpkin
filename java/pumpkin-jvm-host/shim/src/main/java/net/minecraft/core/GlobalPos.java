package net.minecraft.core;

import com.mojang.serialization.Codec;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public record GlobalPos(ResourceKey<Level> dimension, BlockPos pos) {

    public static final Codec<GlobalPos> CODEC = null;

    public static GlobalPos of(ResourceKey<Level> dimension, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/core/GlobalPos.of:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/core/GlobalPos;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/core/GlobalPos.toString:()Ljava/lang/String;");
    }
}
