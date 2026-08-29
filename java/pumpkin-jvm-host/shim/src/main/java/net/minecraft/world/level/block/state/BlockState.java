package net.minecraft.world.level.block.state;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.common.extensions.IBlockStateExtension;
import dev.pumpkin.shim.Unimplemented;

public class BlockState extends BlockBehaviour.BlockStateBase implements IBlockStateExtension {

    public BlockState(Block owner, Property<?>[] propertyKeys, Comparable<?>[] propertyValues) {
    }

    protected BlockState asState() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockState.asState:()Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public BlockState() {
    }
}
