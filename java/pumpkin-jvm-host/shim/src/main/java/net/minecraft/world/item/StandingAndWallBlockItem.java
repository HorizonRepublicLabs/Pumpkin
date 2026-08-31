package net.minecraft.world.item;

import java.util.Map;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class StandingAndWallBlockItem extends BlockItem {

    public StandingAndWallBlockItem(Block block, Block wallBlock, Direction attachmentDirection, Item.Properties properties) {
    }

    protected BlockState getPlacementState(BlockPlaceContext context) {
        throw Unimplemented.forMember("net/minecraft/world/item/StandingAndWallBlockItem.getPlacementState:(Lnet/minecraft/world/item/context/BlockPlaceContext;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public void registerBlocks(Map<Block, Item> map, Item item) {
        throw Unimplemented.forMember("net/minecraft/world/item/StandingAndWallBlockItem.registerBlocks:(Ljava/util/Map;Lnet/minecraft/world/item/Item;)V");
    }

    public StandingAndWallBlockItem() {
    }
}
