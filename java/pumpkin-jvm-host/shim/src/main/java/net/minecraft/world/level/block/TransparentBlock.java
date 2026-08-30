package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public class TransparentBlock extends HalfTransparentBlock {

    public TransparentBlock(BlockBehaviour.Properties properties) {
        // Pumpkin divergence: chains the properties up. Without this the block's
        // template (and everything else recorded on Properties) silently resets
        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.
        super(properties);
    }

    protected MapCodec<? extends TransparentBlock> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/TransparentBlock.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    protected VoxelShape getVisualShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/TransparentBlock.getVisualShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    protected float getShadeBrightness(BlockState state, BlockGetter level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/TransparentBlock.getShadeBrightness:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)F");
    }

    protected boolean propagatesSkylightDown(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/TransparentBlock.propagatesSkylightDown:(Lnet/minecraft/world/level/block/state/BlockState;)Z");
    }

    public TransparentBlock() {
    }
}
