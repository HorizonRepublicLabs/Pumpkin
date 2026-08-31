package net.minecraft.world.item;

import net.minecraft.world.level.block.Block;
import dev.pumpkin.shim.Unimplemented;

public class BannerItem extends StandingAndWallBlockItem {

    public BannerItem(Block block, Block wallBlock, Item.Properties properties) {
    }

    public DyeColor getColor() {
        throw Unimplemented.forMember("net/minecraft/world/item/BannerItem.getColor:()Lnet/minecraft/world/item/DyeColor;");
    }

    public BannerItem() {
    }
}
