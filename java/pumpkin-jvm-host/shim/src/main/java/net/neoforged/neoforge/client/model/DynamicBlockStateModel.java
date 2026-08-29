package net.neoforged.neoforge.client.model;

import java.util.List;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public interface DynamicBlockStateModel extends BlockStateModel {

    default void collectParts(RandomSource random, List<BlockStateModelPart> parts) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/DynamicBlockStateModel.collectParts:(Lnet/minecraft/util/RandomSource;Ljava/util/List;)V");
    }

    void collectParts(BlockAndTintGetter level, BlockPos pos, BlockState state, RandomSource random, List<BlockStateModelPart> parts);
}
