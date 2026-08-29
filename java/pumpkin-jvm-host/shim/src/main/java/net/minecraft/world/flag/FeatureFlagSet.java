package net.minecraft.world.flag;

import java.util.Collection;
import dev.pumpkin.shim.Unimplemented;

public final class FeatureFlagSet {

    private FeatureFlagSet(FeatureFlagUniverse universe, long mask) {
        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.<init>:(Lnet/minecraft/world/flag/FeatureFlagUniverse;J)V");
    }

    private FeatureFlagSet(FeatureFlagUniverse universe, long mask, long[] extendedMask) {
        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.<init>:(Lnet/minecraft/world/flag/FeatureFlagUniverse;J[J)V");
    }

    static FeatureFlagSet create(FeatureFlagUniverse universe, Collection<FeatureFlag> flags) {
        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.create:(Lnet/minecraft/world/flag/FeatureFlagUniverse;Ljava/util/Collection;)Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public static FeatureFlagSet of() {
        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.of:()Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public static FeatureFlagSet of(FeatureFlag flag) {
        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.of:(Lnet/minecraft/world/flag/FeatureFlag;)Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public static FeatureFlagSet of(FeatureFlag flag, FeatureFlag... flags) {
        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.of:(Lnet/minecraft/world/flag/FeatureFlag;[Lnet/minecraft/world/flag/FeatureFlag;)Lnet/minecraft/world/flag/FeatureFlagSet;");
    }

    public boolean contains(FeatureFlag flag) {
        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.contains:(Lnet/minecraft/world/flag/FeatureFlag;)Z");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.isEmpty:()Z");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.hashCode:()I");
    }

    protected FeatureFlagSet() {
    }
}
