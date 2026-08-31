package net.neoforged.neoforge.client.extensions;

import java.util.List;
import net.minecraft.client.renderer.block.BlockAndTintGetter;
import net.minecraft.client.renderer.block.dispatch.BlockStateModelPart;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public interface BlockStateModelExtension {

    default void collectParts(BlockAndTintGetter level, BlockPos pos, BlockState state, RandomSource random, List<BlockStateModelPart> parts) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/BlockStateModelExtension.collectParts:(Lnet/minecraft/client/renderer/block/BlockAndTintGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/util/RandomSource;Ljava/util/List;)V");
    }

    default Material.Baked particleMaterial(BlockAndTintGetter level, BlockPos pos, BlockState state) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/BlockStateModelExtension.particleMaterial:(Lnet/minecraft/client/renderer/block/BlockAndTintGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/client/resources/model/sprite/Material$Baked;");
    }

    default int materialFlags(BlockAndTintGetter level, BlockPos pos, BlockState state) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/BlockStateModelExtension.materialFlags:(Lnet/minecraft/client/renderer/block/BlockAndTintGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)I");
    }

    default boolean hasMaterialFlag(BlockAndTintGetter level, BlockPos pos, BlockState state, int flag) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/BlockStateModelExtension.hasMaterialFlag:(Lnet/minecraft/client/renderer/block/BlockAndTintGetter;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;I)Z");
    }
}
