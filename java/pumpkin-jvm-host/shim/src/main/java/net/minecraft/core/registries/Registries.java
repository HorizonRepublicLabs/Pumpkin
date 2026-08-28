package net.minecraft.core.registries;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;

/** The registry keys a mod names. Only those Pumpkin can service are present. */
public final class Registries {
    public static final ResourceKey<Block> BLOCK =
            ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("minecraft", "block"));

    private Registries() {
    }
}
