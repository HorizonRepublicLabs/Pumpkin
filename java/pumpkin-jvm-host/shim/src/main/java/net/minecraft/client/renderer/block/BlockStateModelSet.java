package net.minecraft.client.renderer.block;

import java.util.Map;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class BlockStateModelSet {

    public BlockStateModelSet(Map<BlockState, BlockStateModel> modelByState, BlockStateModel missingModel) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockStateModelSet.<init>:(Ljava/util/Map;Lnet/minecraft/client/renderer/block/dispatch/BlockStateModel;)V");
    }

    public BlockStateModel get(BlockState state) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockStateModelSet.get:(Lnet/minecraft/world/level/block/state/BlockState;)Lnet/minecraft/client/renderer/block/dispatch/BlockStateModel;");
    }

    protected BlockStateModelSet() {
    }
}
