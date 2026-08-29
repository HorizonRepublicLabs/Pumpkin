package net.minecraft.world;

import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public interface InteractionResult {

    InteractionResult.Success SUCCESS = null;

    InteractionResult.Fail FAIL = null;

    InteractionResult.Pass PASS = null;

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
