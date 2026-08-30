package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import dev.pumpkin.shim.Unimplemented;

public class SkullBlock extends AbstractSkullBlock {

    public MapCodec<? extends SkullBlock> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SkullBlock.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public SkullBlock(SkullBlock.Type type, BlockBehaviour.Properties properties) {
    }

    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SkullBlock.getShape:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/phys/shapes/CollisionContext;)Lnet/minecraft/world/phys/shapes/VoxelShape;");
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SkullBlock.getStateForPlacement:(Lnet/minecraft/world/item/context/BlockPlaceContext;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    protected BlockState rotate(BlockState state, Rotation rotation) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SkullBlock.rotate:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/Rotation;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    protected BlockState mirror(BlockState state, Mirror mirror) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/SkullBlock.mirror:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/Mirror;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    // Pumpkin divergence: declares nothing here. The vanilla declaration needs
    // property constants this shim does not carry yet; a subclass registering
    // through this base gets a single state until they exist, rather than a
    // constructor crash.
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    }

    public interface Type extends StringRepresentable {
    }

    public enum Types implements SkullBlock.Type {

        SKELETON,
        WITHER_SKELETON,
        PLAYER,
        ZOMBIE,
        CREEPER,
        PIGLIN,
        DRAGON;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/SkullBlock$Types.getSerializedName:()Ljava/lang/String;");
        }
    }

    public SkullBlock() {
    }
}
