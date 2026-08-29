package net.minecraft.client.renderer.block.dispatch.multipart;

import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.client.renderer.block.dispatch.BlockStateModel;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.StateHolder;
import dev.pumpkin.shim.Unimplemented;

public record Selector(Optional<Condition> condition, BlockStateModel.Unbaked variant) {

    public <O, S extends StateHolder<O, S>> Predicate<S> instantiate(StateDefinition<O, S> definition) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/block/dispatch/multipart/Selector.instantiate:(Lnet/minecraft/world/level/block/state/StateDefinition;)Ljava/util/function/Predicate;");
    }
}
