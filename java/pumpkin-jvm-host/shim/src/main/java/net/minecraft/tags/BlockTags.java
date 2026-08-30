package net.minecraft.tags;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import dev.pumpkin.shim.Unimplemented;

public final class BlockTags {

    // Pumpkin divergence: real value, named as vanilla names it.
    public static final TagKey<Block> CROPS = create(Identifier.fromNamespaceAndPath("minecraft", "crops"));

    protected BlockTags() {
    }

    private static TagKey<Block> create(String name) {
        throw Unimplemented.forMember("net/minecraft/tags/BlockTags.create:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
    }

    // Pumpkin divergence: real body -- TagKey.create over the block registry's key.
    public static TagKey<Block> create(Identifier name) {
        return TagKey.create(net.minecraft.resources.ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("minecraft", "block")), name);
    }

}
