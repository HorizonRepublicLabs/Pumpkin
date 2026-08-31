package net.minecraft.util.context;

import java.util.Set;
import dev.pumpkin.shim.Unimplemented;

public class ContextKeySet {

    public static final ContextKeySet EMPTY = null;

    private ContextKeySet(Set<ContextKey<?>> required, Set<ContextKey<?>> optional) {
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/util/context/ContextKeySet.toString:()Ljava/lang/String;");
    }

    public static class Builder {

        public ContextKeySet build() {
            throw Unimplemented.forMember("net/minecraft/util/context/ContextKeySet$Builder.build:()Lnet/minecraft/util/context/ContextKeySet;");
        }

        public Builder() {
        }
    }

    public ContextKeySet() {
    }
}
