package net.minecraft.world.flag;

import java.util.Collection;
import dev.pumpkin.shim.Unimplemented;

public final class FeatureFlagSet {

    private FeatureFlagSet(FeatureFlagUniverse universe, long mask) {
    }

    private FeatureFlagSet(FeatureFlagUniverse universe, long mask, long[] extendedMask) {
    }

    static FeatureFlagSet create(FeatureFlagUniverse universe, Collection<FeatureFlag> flags) {
        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.create:(Lnet/minecraft/world/flag/FeatureFlagUniverse;Ljava/util/Collection;)Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    // Pumpkin divergence: real bodies for the empty set. Every other way to build a
    // FeatureFlagSet still throws, so every reachable instance IS the empty set and the
    // instance methods below can answer honestly.
    private static final FeatureFlagSet PUMPKIN_EMPTY = new FeatureFlagSet();

    public static FeatureFlagSet of() {
        return PUMPKIN_EMPTY;
    }

    public static FeatureFlagSet of(FeatureFlag flag) {
        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.of:(Lnet/minecraft/world/flag/FeatureFlag;)Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public static FeatureFlagSet of(FeatureFlag flag, FeatureFlag... flags) {
        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.of:(Lnet/minecraft/world/flag/FeatureFlag;[Lnet/minecraft/world/flag/FeatureFlag;)Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public boolean contains(FeatureFlag flag) {
        return false;
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean equals(Object o) {
        return o instanceof FeatureFlagSet;
    }

    public int hashCode() {
        return 0;
    }

    public FeatureFlagSet() {
    }
}
