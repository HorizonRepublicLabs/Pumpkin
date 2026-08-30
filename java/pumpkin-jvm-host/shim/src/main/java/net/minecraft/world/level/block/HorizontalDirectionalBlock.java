package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import dev.pumpkin.shim.Unimplemented;

public abstract class HorizontalDirectionalBlock extends Block {

    // Pumpkin divergence: real value. TinkeringTableBlock's constructor passes this to
    // setValue -- null here was an NPE inside Map.copyOf naming nothing.
    public static final EnumProperty<Direction> FACING = EnumProperty.create("facing", Direction.class);

    public HorizontalDirectionalBlock(BlockBehaviour.Properties properties) {
        // Pumpkin divergence: chains the properties up. Without this the block's
        // template (and everything else recorded on Properties) silently resets
        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.
        super(properties);
    }

    protected abstract MapCodec<? extends HorizontalDirectionalBlock> codec();

    protected BlockState rotate(BlockState state, Rotation rotation) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/HorizontalDirectionalBlock.rotate:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/Rotation;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    protected BlockState mirror(BlockState state, Mirror mirror) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/HorizontalDirectionalBlock.mirror:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/Mirror;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    public HorizontalDirectionalBlock() {
    }
}
