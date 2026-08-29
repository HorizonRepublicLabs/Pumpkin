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
                w.write("CLASS\t" + entry.getKey() + "\t" + String.join(",", entry.getValue()) + "\n");
            }
            for (Map.Entry<MemberRef, TreeSet<String>> entry : memberReferrers.entrySet()) {
                w.write("MEMBER\t" + entry.getKey().key() + "\t" + String.join(",", entry.getValue()) + "\n");
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * Parses exactly what {@link #writeTo} produces. Any line that is not a {@code
     * CLASS} or {@code MEMBER} line is rejected loudly: a silently skipped line would
     * mean data loss that the round-trip test would never catch.
     */
    public static UsedSet readFrom(Reader r) {
        UsedSet used = new UsedSet();
        try (BufferedReader br = new BufferedReader(r)) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\t", 3);
                String kind = parts[0];
                if (!kind.equals("CLASS") && !kind.equals("MEMBER")) {
                    throw new IllegalArgumentException("Not a CLASS or MEMBER line: " + line);
                }
                if (parts.length < 2) {
                    throw new IllegalArgumentException("Not a CLASS or MEMBER line: " + line);
                }
                String key = parts[1];
                String referrersField = parts.length > 2 ? parts[2] : "";
                String[] referrers = referrersField.isEmpty() ? new String[0] : referrersField.split(",");
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
