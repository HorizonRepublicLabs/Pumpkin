package net.minecraft.world.level.block.state;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.common.extensions.IBlockStateExtension;
import dev.pumpkin.shim.Unimplemented;

public class BlockState extends BlockBehaviour.BlockStateBase implements IBlockStateExtension {

    public static final Codec<BlockState> CODEC = null;

    public BlockState(Block owner, Property<?>[] propertyKeys, Comparable<?>[] propertyValues) {
    }

    protected BlockState asState() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockState.asState:()Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public BlockState() {
    }

    // Pumpkin divergence: setValue's copy keeps the owning block and the values.
    @Override
    protected net.minecraft.world.level.block.state.StateHolder<net.minecraft.world.level.block.Block, BlockState> pumpkinSibling() {
        BlockState sibling = new BlockState();
        sibling.pumpkinOwner = this.pumpkinOwner;
        sibling.pumpkinValues = this.pumpkinValues;
        return sibling;
    }
}
