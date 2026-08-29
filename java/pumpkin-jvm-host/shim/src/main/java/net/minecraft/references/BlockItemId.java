package net.minecraft.references;

import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import dev.pumpkin.shim.Unimplemented;

public record BlockItemId(ResourceKey<Block> block, ResourceKey<Item> item) {

    public static BlockItemId create(Identifier blockId, Identifier itemId) {
        throw Unimplemented.forMember("net/minecraft/references/BlockItemId.create:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/Identifier;)Lnet/minecraft/references/BlockItemId;");
    }

    public static BlockItemId create(String blockName, String itemName) {
        throw Unimplemented.forMember("net/minecraft/references/BlockItemId.create:(Ljava/lang/String;Ljava/lang/String;)Lnet/minecraft/references/BlockItemId;");
    }

    public static BlockItemId create(String name) {
        throw Unimplemented.forMember("net/minecraft/references/BlockItemId.create:(Ljava/lang/String;)Lnet/minecraft/references/BlockItemId;");
    }
}
