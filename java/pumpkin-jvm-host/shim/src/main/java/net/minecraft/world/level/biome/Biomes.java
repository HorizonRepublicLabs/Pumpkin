package net.minecraft.world.level.biome;

import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public abstract class Biomes {

    public static final ResourceKey<Biome> PLAINS = null;

    private static ResourceKey<Biome> register(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/biome/Biomes.register:(Ljava/lang/String;)Lnet/minecraft/resources/ResourceKey;");
    }

    public Biomes() {
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/world/level/biome/Biomes");
        }
    }
}
