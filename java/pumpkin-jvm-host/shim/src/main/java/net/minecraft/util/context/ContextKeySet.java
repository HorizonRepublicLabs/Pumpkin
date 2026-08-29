package net.minecraft.util.context;

import java.util.Set;
import dev.pumpkin.shim.Unimplemented;

public class ContextKeySet {

    private ContextKeySet(Set<ContextKey<?>> required, Set<ContextKey<?>> optional) {
        throw Unimplemented.forMember("net/minecraft/util/context/ContextKeySet.<init>:(Ljava/util/Set;Ljava/util/Set;)V");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/util/context/ContextKeySet.toString:()Ljava/lang/String;");
    }

    public static class Builder {

        public ContextKeySet build() {
            throw Unimplemented.forMember("net/minecraft/util/context/ContextKeySet$Builder.build:()Lnet/minecraft/util/context/ContextKeySet;");
        }

        protected Builder() {
        }
    }

    protected ContextKeySet() {
    }
}
