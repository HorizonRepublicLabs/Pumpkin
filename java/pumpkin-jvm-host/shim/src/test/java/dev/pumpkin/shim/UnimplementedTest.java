package dev.pumpkin.shim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnimplementedTest {
    private static final String KEY =
            "net/minecraft/world/item/ItemStack.hurtAndBreak:(ILnet/minecraft/world/entity/LivingEntity;Lnet/minecraft/world/InteractionHand;)V";

    @BeforeEach
    void clearHits() {
        Unimplemented.resetHits();
    }

    /// Unchecked, or every generated signature would need a `throws` clause and
    /// would stop overriding the supertype it reproduces.
    @Test
    void isUnchecked() {
        assertTrue(RuntimeException.class.isAssignableFrom(Unimplemented.class));
    }

    /// The message is the manifest key verbatim, including the descriptor, because
    /// ItemStack.hurtAndBreak has three overloads and a bare method name cannot say
    /// which one a mod reached.
    @Test
    void carriesTheFullMemberKey() {
        Unimplemented thrown = new Unimplemented(KEY);
        assertEquals(KEY, thrown.memberKey());
        assertEquals(KEY, thrown.getMessage());
    }

    /// Constructing one records it, so subtracting hits from the manifest gives the
    /// burndown.
    @Test
    void recordsEveryKeyItWasConstructedWith() {
        new Unimplemented(KEY);
        new Unimplemented("net/minecraft/world/level/Level.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;");
        assertEquals(2, Unimplemented.hits().size());
        assertTrue(Unimplemented.hits().contains(KEY));
    }

    /// The registry is read from tests and written from many mod threads.
    @Test
    void hitsAreNotMutableByCallers() {
        new Unimplemented(KEY);
        var hits = Unimplemented.hits();
        try {
            hits.clear();
        } catch (UnsupportedOperationException expected) {
            // an unmodifiable view is the intent
        }
        assertTrue(Unimplemented.hits().contains(KEY), "the registry survived a caller clearing its view");
    }
}
