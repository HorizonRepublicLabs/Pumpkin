package net.minecraft.client.renderer.block;

import net.minecraft.client.renderer.block.model.BlockDisplayContext;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.level.block.state.BlockState;
import dev.pumpkin.shim.Unimplemented;

public class BlockModelResolver {

    public BlockModelResolver(ModelManager modelManager) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockModelResolver.<init>:(Lnet/minecraft/client/resources/model/ModelManager;)V");
    }

    public void update(BlockModelRenderState renderState, BlockState blockState, BlockDisplayContext displayContext) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/BlockModelResolver.update:(Lnet/minecraft/client/renderer/block/BlockModelRenderState;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/client/renderer/block/model/BlockDisplayContext;)V");
    }

    protected BlockModelResolver() {
    }
}
