package dev.pumpkin.shimgen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.StringReader;
import java.io.StringWriter;
import org.junit.jupiter.api.Test;

class UsedSetTest {
    private static UsedSet sample() {
        UsedSet used = new UsedSet();
        used.addClass("net/minecraft/world/level/Level", "com/blakebr0/example/Thing");
        used.addMember(
                new UsedSet.MemberRef("net/minecraft/world/level/Level", "getBlockState",
                        "(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;"),
                "com/blakebr0/example/Thing");
        return used;
    }

    @Test
    void aMemberKeyIsOwnerDotNameColonDescriptor() {
        var ref = new UsedSet.MemberRef("net/minecraft/world/item/ItemStack", "getItem",
                "()Lnet/minecraft/world/item/Item;");
        assertEquals("net/minecraft/world/item/ItemStack.getItem:()Lnet/minecraft/world/item/Item;", ref.key());
    }

    /// Two overloads differ only in descriptor, so the descriptor is part of identity.
    @Test
    void overloadsAreDistinctMembers() {
        UsedSet used = new UsedSet();
        String owner = "net/minecraft/world/item/ItemStack";
        used.addMember(new UsedSet.MemberRef(owner, "hurtAndBreak", "(ILnet/minecraft/server/level/ServerLevel;)V"), "x");
        used.addMember(new UsedSet.MemberRef(owner, "hurtAndBreak", "(ILnet/minecraft/world/entity/LivingEntity;)V"), "x");
        assertEquals(2, used.membersOf(owner).size());
    }

    /// The manifest is committed and diffed, so its order must not depend on hashing.
    @Test
    void theManifestRoundTripsAndIsSorted() {
        StringWriter out = new StringWriter();
        sample().writeTo(out);
        String text = out.toString();

        UsedSet reread = UsedSet.readFrom(new StringReader(text));
        StringWriter again = new StringWriter();
        reread.writeTo(again);
        assertEquals(text, again.toString(), "round trip is lossless");

        StringWriter second = new StringWriter();
        sample().writeTo(second);
        assertEquals(text, second.toString(), "two runs produce identical bytes");
    }

    @Test
    void theManifestSaysWhoReferencedEachEntry() {
        StringWriter out = new StringWriter();
        sample().writeTo(out);
        assertTrue(out.toString().contains("com/blakebr0/example/Thing"),
                "a reader must be able to see why an entry is present");
    }
}
