package net.minecraft.client.renderer.block.dispatch.multipart;

import java.util.function.Predicate;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.StateHolder;

public interface Condition {

    <O, S extends StateHolder<O, S>> Predicate<S> instantiate(StateDefinition<O, S> definition);
}
