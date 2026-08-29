package dev.pumpkin.shim;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Thrown by a generated shim member that has no implementation yet.
 *
 * <p>Unchecked on purpose: a checked exception would force `throws` clauses onto
 * generated signatures, and a generated method that declares a checked exception its
 * supertype does not no longer overrides it.
 *
 * <p>Never caught-and-defaulted anywhere in the shim. A member that returns a
 * plausible zero produces a mod that runs and is quietly wrong, which is worse than
 * one that stops.
 */
public final class Unimplemented extends RuntimeException {
    private static final Set<String> HITS = ConcurrentHashMap.newKeySet();

    private final String memberKey;

    /**
     * @param memberKey the manifest's key for this member, descriptor included, e.g.
     *                  {@code net/minecraft/world/level/Level.getBlockState:(Lnet/minecraft/core/BlockPos;)Lnet/minecraft/world/level/block/state/BlockState;}
     */
    public Unimplemented(String memberKey) {
        super(memberKey);
        this.memberKey = memberKey;
    }

    /**
     * Records {@code memberKey} as hit and returns an {@code Unimplemented} carrying it.
     *
     * <p>Generated shim members must throw this, not {@code new Unimplemented(key)}
     * directly: recording belongs at the throw site, so a key is only ever counted
     * when it actually escapes as a thrown exception, never when one is merely
     * constructed (e.g. by future logging or diagnostic code) and discarded.
     *
     * <p>Returns {@code RuntimeException}, not {@code Unimplemented}, so the call site
     * reads naturally as {@code throw Unimplemented.forMember(key);}.
     */
    public static RuntimeException forMember(String memberKey) {
        HITS.add(memberKey);
        return new Unimplemented(memberKey);
    }

    /** The key this was constructed with. Joins against the committed manifest. */
    public String memberKey() {
        return memberKey;
    }

    /** Every key thrown so far in this JVM. Manifest minus this is the burndown. */
    public static Set<String> hits() {
        return Collections.unmodifiableSet(HITS);
    }

    /** Clears the registry. Package-private: test-only by visibility, not by request. */
    static void resetHits() {
        HITS.clear();
    }
}
