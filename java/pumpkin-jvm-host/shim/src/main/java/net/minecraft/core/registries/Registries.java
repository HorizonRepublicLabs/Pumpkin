package net.minecraft.core.registries;

import net.minecraft.core.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;

/** The registry keys a mod names. Only those Pumpkin can service are present. */
public final class Registries {
    public static final ResourceKey<Block> BLOCK =
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath("minecraft", "block"));

    private Registries() {
    }
}
