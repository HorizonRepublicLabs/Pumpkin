package net.minecraft.tags;

import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import dev.pumpkin.shim.Unimplemented;

public final class BlockTags {

    public static final TagKey<Block> CROPS = null;

    protected BlockTags() {
    }

    private static TagKey<Block> create(String name) {
        throw Unimplemented.forMember("net/minecraft/tags/BlockTags.create:(Ljava/lang/String;)Lnet/minecraft/tags/TagKey;");
    }

    public static TagKey<Block> create(Identifier name) {
        throw Unimplemented.forMember("net/minecraft/tags/BlockTags.create:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/tags/TagKey;");
    }

    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/tags/BlockTags");
        }
    }
}
