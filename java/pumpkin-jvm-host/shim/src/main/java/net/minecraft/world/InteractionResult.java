package net.minecraft.world;

import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public interface InteractionResult {

    // Pumpkin divergence: real instances -- vanilla's own values. A null here made every
    // handler's return indistinguishable from every other.
    InteractionResult.Success SUCCESS = new Success(SwingSource.CLIENT, new ItemContext(true, null));

    InteractionResult.Fail FAIL = new Fail();

    InteractionResult.Pass PASS = new Pass();

    record Fail() implements InteractionResult {
    }

    record ItemContext(boolean wasItemInteraction, ItemStack heldItemTransformedTo) {
    }

    record Pass() implements InteractionResult {
    }

    record Success(InteractionResult.SwingSource swingSource, InteractionResult.ItemContext itemContext) implements InteractionResult {

        public boolean consumesAction() {
            throw Unimplemented.forMember("net/minecraft/world/InteractionResult$Success.consumesAction:()Z");
        }
    }

    enum SwingSource {

        NONE, CLIENT, SERVER
    }

    record TryEmptyHandInteraction() implements InteractionResult {
    }
}
