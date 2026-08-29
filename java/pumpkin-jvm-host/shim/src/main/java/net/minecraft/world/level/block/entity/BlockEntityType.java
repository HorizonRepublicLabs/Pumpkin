package net.minecraft.world.level.block.entity;

import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class BlockEntityType<T extends BlockEntity> {

    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, Set<Block> validBlocks) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntityType.<init>:(Lnet/minecraft/world/level/block/entity/BlockEntityType$BlockEntitySupplier;Ljava/util/Set;)V");
    }

    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, Set<Block> validBlocks, boolean onlyOpCanSetNbt) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntityType.<init>:(Lnet/minecraft/world/level/block/entity/BlockEntityType$BlockEntitySupplier;Ljava/util/Set;Z)V");
    }

    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, Block... validBlocks) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntityType.<init>:(Lnet/minecraft/world/level/block/entity/BlockEntityType$BlockEntitySupplier;[Lnet/minecraft/world/level/block/Block;)V");
    }

    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, boolean onlyOpCanSetNbt, Block... validBlocks) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntityType.<init>:(Lnet/minecraft/world/level/block/entity/BlockEntityType$BlockEntitySupplier;Z[Lnet/minecraft/world/level/block/Block;)V");
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

    protected BlockEntityType() {
    }
}
