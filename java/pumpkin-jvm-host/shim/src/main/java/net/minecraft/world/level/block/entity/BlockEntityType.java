package net.minecraft.world.level.block.entity;

import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class BlockEntityType<T extends BlockEntity> {

    // Pumpkin divergence: the valid blocks are kept, not discarded. They are how the
    // registration sink learns which placed block should get this entity -- the type
    // registers after its blocks, so the link has to travel this way.
    private final java.util.List<Block> pumpkinValidBlocks = new java.util.ArrayList<>();

    // Pumpkin divergence: the factory too -- it is how the interaction bridge builds the
    // mod's own tile entity when its block is used.
    private BlockEntityType.BlockEntitySupplier<? extends T> pumpkinFactory;

    public T pumpkinCreate(BlockPos worldPosition, BlockState blockState) {
        return pumpkinFactory.create(worldPosition, blockState);
    }

    public java.util.List<Block> pumpkinValidBlocks() {
        return pumpkinValidBlocks;
    }

    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, Set<Block> validBlocks) {
        pumpkinValidBlocks.addAll(validBlocks);
        this.pumpkinFactory = factory;
    }

    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, Set<Block> validBlocks, boolean onlyOpCanSetNbt) {
        pumpkinValidBlocks.addAll(validBlocks);
        this.pumpkinFactory = factory;
    }

    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, Block... validBlocks) {
        java.util.Collections.addAll(pumpkinValidBlocks, validBlocks);
        this.pumpkinFactory = factory;
    }

    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, boolean onlyOpCanSetNbt, Block... validBlocks) {
        java.util.Collections.addAll(pumpkinValidBlocks, validBlocks);
        this.pumpkinFactory = factory;
    }

    public T create(BlockPos worldPosition, BlockState blockState) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntityType.create:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/world/level/block/entity/BlockEntity;");
    }

    public T getBlockEntity(BlockGetter level, BlockPos pos) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntityType.getBlockEntity:(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/entity/BlockEntity;");
    }

    public interface BlockEntitySupplier<T extends BlockEntity> {

        T create(BlockPos worldPosition, BlockState blockState);
    }

    public BlockEntityType() {
    }
}
