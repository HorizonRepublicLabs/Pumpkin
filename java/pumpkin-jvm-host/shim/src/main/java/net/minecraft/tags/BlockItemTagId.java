package net.minecraft.tags;

import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import dev.pumpkin.shim.Unimplemented;

public record BlockItemTagId(TagKey<Block> block, TagKey<Item> item) {

    public static BlockItemTagId create(Identifier blockId, Identifier itemId) {
        throw Unimplemented.forMember("net/minecraft/tags/BlockItemTagId.create:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/Identifier;)Lnet/minecraft/tags/BlockItemTagId;");
    }

    public static BlockItemTagId create(String blockName, String itemName) {
        throw Unimplemented.forMember("net/minecraft/tags/BlockItemTagId.create:(Ljava/lang/String;Ljava/lang/String;)Lnet/minecraft/tags/BlockItemTagId;");
    }

    public static BlockItemTagId create(String name) {
        throw Unimplemented.forMember("net/minecraft/tags/BlockItemTagId.create:(Ljava/lang/String;)Lnet/minecraft/tags/BlockItemTagId;");
    }
}
