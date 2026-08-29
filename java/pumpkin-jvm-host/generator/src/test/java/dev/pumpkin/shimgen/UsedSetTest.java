package dev.pumpkin.shimgen;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

    /// A blank line is neither CLASS nor MEMBER, so it must be rejected like any
    /// other malformed line rather than silently skipped.
    @Test
    void aBlankLineIsRejected() {
        StringWriter out = new StringWriter();
        sample().writeTo(out);
        String withBlankLine = out.toString() + "\n";

        assertThrows(IllegalArgumentException.class, () -> UsedSet.readFrom(new StringReader(withBlankLine)));
    }

    /// Every entry this generator ever produces has at least one referrer by
    /// construction, so a line with none is corrupt input, not a legitimate entry.
    @Test
    void aLineWithNoReferrersIsRejected() {
        String noReferrers = "CLASS\tnet/minecraft/world/level/Level\t\n";

        assertThrows(IllegalArgumentException.class, () -> UsedSet.readFrom(new StringReader(noReferrers)));
    }

    /// A missing referrers field entirely (no trailing tab at all) is just as corrupt.
    @Test
    void aLineWithNoReferrersFieldAtAllIsRejected() {
        String noReferrersField = "CLASS\tnet/minecraft/world/level/Level\n";

        assertThrows(IllegalArgumentException.class, () -> UsedSet.readFrom(new StringReader(noReferrersField)));
    }

    /// A referrer containing a tab or comma would corrupt the line format and
    /// misparse silently on the way back in, so writeTo must refuse to emit it.
    @Test
    void writeToRejectsAReferrerContainingATabOrComma() {
        UsedSet commaReferrer = new UsedSet();
        commaReferrer.addClass("net/minecraft/world/level/Level", "com/blakebr0/example,Thing");
        assertThrows(IllegalArgumentException.class, () -> commaReferrer.writeTo(new StringWriter()));

        UsedSet tabReferrer = new UsedSet();
        tabReferrer.addClass("net/minecraft/world/level/Level", "com/blakebr0/example\tThing");
        assertThrows(IllegalArgumentException.class, () -> tabReferrer.writeTo(new StringWriter()));
    }
}
