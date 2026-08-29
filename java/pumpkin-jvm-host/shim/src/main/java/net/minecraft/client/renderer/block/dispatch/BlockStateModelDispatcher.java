package net.minecraft.client.renderer.block.dispatch;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import net.minecraft.client.renderer.block.dispatch.multipart.MultiPartModel;
import net.minecraft.client.renderer.block.dispatch.multipart.Selector;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import dev.pumpkin.shim.Unimplemented;

public record BlockStateModelDispatcher(Optional<BlockStateModelDispatcher.SimpleModelSelectors> simpleModels, Optional<BlockStateModelDispatcher.MultiPartDefinition> multiPart, Optional<net.neoforged.neoforge.client.model.block.CustomBlockModelDefinition> customDefinition) {

    public BlockStateModelDispatcher(Optional<BlockStateModelDispatcher.SimpleModelSelectors> simpleModels, Optional<BlockStateModelDispatcher.MultiPartDefinition> multiPart) {
        this((Optional<BlockStateModelDispatcher.SimpleModelSelectors>) null, (Optional<BlockStateModelDispatcher.MultiPartDefinition>) null, (Optional<net.neoforged.neoforge.client.model.block.CustomBlockModelDefinition>) null);
    }

    public BlockStateModelDispatcher(net.neoforged.neoforge.client.model.block.CustomBlockModelDefinition customDefinition) {
        this((Optional<BlockStateModelDispatcher.SimpleModelSelectors>) null, (Optional<BlockStateModelDispatcher.MultiPartDefinition>) null, (Optional<net.neoforged.neoforge.client.model.block.CustomBlockModelDefinition>) null);
    }

    public Map<BlockState, BlockStateModel.UnbakedRoot> instantiate(StateDefinition<Block, BlockState> stateDefinition, Supplier<String> source) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/BlockStateModelDispatcher.instantiate:(Lnet/minecraft/world/level/block/state/StateDefinition;Ljava/util/function/Supplier;)Ljava/util/Map;");
    }

    public record MultiPartDefinition(List<Selector> selectors) {

        public MultiPartModel.Unbaked instantiate(StateDefinition<Block, BlockState> stateDefinition) {
            throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/BlockStateModelDispatcher$MultiPartDefinition.instantiate:(Lnet/minecraft/world/level/block/state/StateDefinition;)Lnet/minecraft/client/renderer/block/dispatch/multipart/MultiPartModel$Unbaked;");
        }
    }

    public record SimpleModelSelectors(Map<String, BlockStateModel.Unbaked> models) {
    }
}
