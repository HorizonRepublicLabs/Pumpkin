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
        throw Unimplemented.forMember("net/minecraft/world/level/block/HalfTransparentBlock.<init>:(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V");
    }

    protected boolean skipRendering(BlockState state, BlockState neighborState, Direction direction) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/HalfTransparentBlock.skipRendering:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/core/Direction;)Z");
    }

    protected HalfTransparentBlock() {
    }
}
