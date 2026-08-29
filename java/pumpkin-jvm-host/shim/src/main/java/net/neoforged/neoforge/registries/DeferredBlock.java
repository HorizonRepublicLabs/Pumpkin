package net.neoforged.neoforge.registries;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import dev.pumpkin.shim.Unimplemented;

public class DeferredBlock<T extends Block> extends DeferredHolder<Block, T> implements ItemLike {

    protected DeferredBlock(ResourceKey<Block> key) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredBlock.<init>:(Lnet/minecraft/resources/ResourceKey;)V");
    }

    public Item asItem() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredBlock.asItem:()Lnet/minecraft/world/item/Item;");
    }

    public DeferredBlock() {
    }
}
