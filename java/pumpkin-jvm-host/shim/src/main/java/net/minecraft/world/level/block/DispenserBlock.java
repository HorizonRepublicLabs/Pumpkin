package net.minecraft.world.level.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.phys.BlockHitResult;
import dev.pumpkin.shim.Unimplemented;

public class DispenserBlock extends BaseEntityBlock {

    public static final EnumProperty<Direction> FACING = EnumProperty.create("facing", Direction.class);

    public MapCodec<? extends DispenserBlock> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/DispenserBlock.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    // Pumpkin divergence: real body. Registers a dispenser behaviour Pumpkin's own

    // dispensers never consult -- accepted and dropped, like the unmodelled registries,

    // so an item can install its dispense logic without stopping its whole mod.

    public static void registerBehavior(ItemLike item, DispenseItemBehavior behavior) {

    }

    public DispenserBlock(BlockBehaviour.Properties properties) {
        // Pumpkin divergence: chains the properties up. Without this the block's
        // template (and everything else recorded on Properties) silently resets
        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.
        super(properties);
    }

    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/DispenserBlock.useWithoutItem:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/phys/BlockHitResult;)Lnet/minecraft/world/InteractionResult;");
    }

    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block, Orientation orientation, boolean movedByPiston) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/DispenserBlock.neighborChanged:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/Block;Lnet/minecraft/world/level/redstone/Orientation;Z)V");
    }

    protected void tick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/DispenserBlock.tick:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V");
    }

    public BlockEntity newBlockEntity(BlockPos worldPosition, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/DispenserBlock.newBlockEntity:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/block/entity/BlockEntity;");
    }

    public BlockState getStateForPlacement(BlockPlaceContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/DispenserBlock.getStateForPlacement:(Lnet/minecraft/world/item/context/BlockPlaceContext;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    protected void affectNeighborsAfterRemoval(BlockState state, ServerLevel level, BlockPos pos, boolean movedByPiston) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/DispenserBlock.affectNeighborsAfterRemoval:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Z)V");
    }

    protected boolean hasAnalogOutputSignal(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/DispenserBlock.hasAnalogOutputSignal:(Lnet/minecraft/world/level/block/state/BlockState;)Z");
    }

    protected int getAnalogOutputSignal(BlockState state, Level level, BlockPos pos, Direction direction) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/DispenserBlock.getAnalogOutputSignal:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)I");
    }

    protected BlockState rotate(BlockState state, Rotation rotation) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/DispenserBlock.rotate:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/Rotation;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    protected BlockState mirror(BlockState state, Mirror mirror) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/DispenserBlock.mirror:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/Mirror;)Lnet/minecraft/world/level/block/state/BlockState;");
    }

    // Pumpkin divergence: declares nothing here. The vanilla declaration needs
    // property constants this shim does not carry yet; a subclass registering
    // through this base gets a single state until they exist, rather than a
    // constructor crash.
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    }

    public DispenserBlock() {
    }
}
