package net.minecraft.world;

import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public interface InteractionResult {

    // Pumpkin divergence: real instances -- vanilla's own values. A null here made every
    // handler's return indistinguishable from every other.
    InteractionResult.Success SUCCESS = new Success(SwingSource.CLIENT, new ItemContext(true, null));

    // Pumpkin divergence: real instance, per vanilla -- swing decided server-side.
    InteractionResult.Success SUCCESS_SERVER = new Success(SwingSource.SERVER, new ItemContext(true, null));

    // Pumpkin divergence: real instance, per vanilla -- success with no swing.
    InteractionResult.Success CONSUME = new Success(SwingSource.NONE, new ItemContext(true, null));

    InteractionResult.Fail FAIL = new Fail();

    InteractionResult.Pass PASS = new Pass();

    // Pumpkin divergence: real instance -- the default useItemOn answer must be
    // distinguishable so the bridge can fall through to useWithoutItem.
    InteractionResult.TryEmptyHandInteraction TRY_WITH_EMPTY_HAND = new TryEmptyHandInteraction();

    default boolean consumesAction() {
        throw Unimplemented.forMember("net/minecraft/world/InteractionResult.consumesAction:()Z");
    }

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

        public InteractionResult.Success heldItemTransformedTo(ItemStack itemStack) {
            throw Unimplemented.forMember("net/minecraft/world/InteractionResult$Success.heldItemTransformedTo:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/InteractionResult$Success;");
        }

        public ItemStack heldItemTransformedTo() {
            throw Unimplemented.forMember("net/minecraft/world/InteractionResult$Success.heldItemTransformedTo:()Lnet/minecraft/world/item/ItemStack;");
        }
    }

    enum SwingSource {

        NONE, CLIENT, SERVER
    }

    record TryEmptyHandInteraction() implements InteractionResult {
    }
}
