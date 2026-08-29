package dev.pumpkin.shimgen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.util.Collections;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * The set of {@code net.minecraft} classes and members that the real mods actually
 * reference, together with which mod referenced each one.
 *
 * <p>This is the data the generator produces and the manifest that gets committed to
 * the repository and reviewed as a diff. Every collection on the path from here to the
 * manifest is a {@link TreeSet}/{@link TreeMap}, never a hash-based collection: two
 * runs over the same inputs must produce byte-identical output, or the committed
 * manifest churns on every regeneration and code review stops working.
 */
public final class UsedSet {
    private final TreeMap<String, TreeSet<String>> classReferrers = new TreeMap<>();
    private final TreeMap<MemberRef, TreeSet<String>> memberReferrers = new TreeMap<>();

    public void addClass(String internalName, String referencedBy) {
        classReferrers.computeIfAbsent(internalName, k -> new TreeSet<>()).add(referencedBy);
    }

    public void addMember(MemberRef ref, String referencedBy) {
        memberReferrers.computeIfAbsent(ref, k -> new TreeSet<>()).add(referencedBy);
    }

    public SortedSet<String> classes() {
        return Collections.unmodifiableSortedSet(new TreeSet<>(classReferrers.keySet()));
    }

    public SortedSet<MemberRef> members() {
        return Collections.unmodifiableSortedSet(new TreeSet<>(memberReferrers.keySet()));
    }

    /**
     * The {@code name:descriptor} of every member of {@code internalName} in this set.
     *
     * <p>Keyed on name and descriptor together, never name alone: overloads that
     * differ only in descriptor (e.g. {@code ItemStack.hurtAndBreak}'s three variants)
     * must remain distinct entries.
     */
    public SortedSet<String> membersOf(String internalName) {
        TreeSet<String> result = new TreeSet<>();
        for (MemberRef ref : memberReferrers.keySet()) {
            if (ref.owner().equals(internalName)) {
                result.add(ref.name() + ":" + ref.descriptor());
            }
        }
        return Collections.unmodifiableSortedSet(result);
    }

    /**
     * Writes the manifest: classes first, then members, each sorted, one entry per
     * line, tab-separated, with a comma-separated (sorted) list of referrers.
     *
     * <pre>
     * CLASS&#9;net/minecraft/world/level/Level&#9;com/blakebr0/example/Thing
     * MEMBER&#9;net/minecraft/world/level/Level.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;&#9;com/blakebr0/example/Thing
     * </pre>
     */
    public void writeTo(Writer w) {
        try {
            for (Map.Entry<String, TreeSet<String>> entry : classReferrers.entrySet()) {
                w.write("CLASS\t" + entry.getKey() + "\t" + joinReferrers(entry.getValue()) + "\n");
            }
            for (Map.Entry<MemberRef, TreeSet<String>> entry : memberReferrers.entrySet()) {
                w.write("MEMBER\t" + entry.getKey().key() + "\t" + joinReferrers(entry.getValue()) + "\n");
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Joins referrers with {@code ,}, first checking that none of them contain a tab
     * or a comma — either would corrupt the tab-separated, comma-joined line format
     * and misparse silently on the way back in.
     */
    private static String joinReferrers(TreeSet<String> referrers) {
        for (String referrer : referrers) {
            if (referrer.indexOf('\t') >= 0 || referrer.indexOf(',') >= 0) {
                throw new IllegalArgumentException(
                        "Referrer contains a tab or comma, which would corrupt the manifest: " + referrer);
            }
        }
        return String.join(",", referrers);
    }

    /**
     * Parses exactly what {@link #writeTo} produces. Any line that is not a {@code
     * CLASS} or {@code MEMBER} line is rejected loudly, blank lines included: a
     * silently skipped line would mean data loss that the round-trip test would never
     * catch. Likewise a {@code CLASS}/{@code MEMBER} line with no referrers is
     * rejected rather than registering a referrer-less entry: every entry this
     * generator ever produces has at least one referrer by construction, so a line
     * without one means the manifest was hand-edited or corrupted, and silently
     * accepting it would be the wrong answer.
     */
    public static UsedSet readFrom(Reader r) {
        UsedSet used = new UsedSet();
        try (BufferedReader br = new BufferedReader(r)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t", 3);
                String kind = parts[0];
                if (!kind.equals("CLASS") && !kind.equals("MEMBER")) {
                    throw new IllegalArgumentException("Not a CLASS or MEMBER line: " + line);
                }
                if (parts.length < 3 || parts[2].isEmpty()) {
                    throw new IllegalArgumentException("Line has no referrers: " + line);
                }
                String key = parts[1];
                String[] referrers = parts[2].split(",");
                if (kind.equals("CLASS")) {
                    for (String referrer : referrers) {
                        used.addClass(key, referrer);
                    }
                } else {
                    MemberRef ref = parseKey(key);
                    for (String referrer : referrers) {
                        used.addMember(ref, referrer);
                    }
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return used;
    }

    private static MemberRef parseKey(String key) {
        int colon = key.indexOf(':');
        String ownerAndName = key.substring(0, colon);
        String descriptor = key.substring(colon + 1);
        int dot = ownerAndName.lastIndexOf('.');
        String owner = ownerAndName.substring(0, dot);
        String name = ownerAndName.substring(dot + 1);
        return new MemberRef(owner, name, descriptor);
    }

    /**
     * A field or method of {@code owner}, identified by {@code name} and {@code
     * descriptor} together. The descriptor is part of identity on purpose: overloads
     * differ only in it, and later tasks join runtime failures against this manifest
     * by exact key.
     */
    public record MemberRef(String owner, String name, String descriptor) implements Comparable<MemberRef> {
        /** {@code owner + "." + name + ":" + descriptor} — the manifest's join key. */
        public String key() {
            return owner + "." + name + ":" + descriptor;
        }

        @Override
        public int compareTo(MemberRef other) {
            return key().compareTo(other.key());
        }
    }
}
