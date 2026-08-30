package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class HalfTransparentBlock extends Block {

    protected MapCodec<? extends HalfTransparentBlock> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/HalfTransparentBlock.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public HalfTransparentBlock(BlockBehaviour.Properties properties) {
        // Pumpkin divergence: chains the properties up. Without this the block's
        // template (and everything else recorded on Properties) silently resets
        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.
        super(properties);
    }

    protected boolean skipRendering(BlockState state, BlockState neighborState, Direction direction) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/HalfTransparentBlock.skipRendering:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;)Z");
    }

    public HalfTransparentBlock() {
    }
}
