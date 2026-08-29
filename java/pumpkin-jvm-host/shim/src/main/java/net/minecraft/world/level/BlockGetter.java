package net.minecraft.world.level;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.neoforge.common.extensions.IBlockGetterExtension;
import dev.pumpkin.shim.Unimplemented;

public interface BlockGetter extends LevelHeightAccessor, IBlockGetterExtension {

    BlockEntity getBlockEntity(BlockPos pos);

    default <T extends BlockEntity> Optional<T> getBlockEntity(BlockPos pos, BlockEntityType<T> type) {
        throw Unimplemented.forMember("net/minecraft/world/level/BlockGetter.getBlockEntity:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntityType;)Ljava/util/Optional;");
    }

    BlockState getBlockState(final BlockPos pos);

    FluidState getFluidState(BlockPos pos);

    interface BlockStepVisitor {

        boolean visit(BlockPos pos, int iteration);
    }
}
