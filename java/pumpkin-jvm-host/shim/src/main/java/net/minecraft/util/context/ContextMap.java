package net.minecraft.util.context;

import java.util.Map;
import dev.pumpkin.shim.Unimplemented;

public class ContextMap {

    public static final ContextMap EMPTY = null;

    private ContextMap(Map<ContextKey<?>, Object> params) {
    }

    public <T> T getOptional(ContextKey<T> key) {
        throw Unimplemented.forMember("net/minecraft/util/context/ContextMap.getOptional:(Lnet/minecraft/util/context/ContextKey;)Ljava/lang/Object;");
    }

    public static class Builder {

        public <T> ContextMap.Builder withParameter(ContextKey<T> param, T value) {
            throw Unimplemented.forMember("net/minecraft/util/context/ContextMap$Builder.withParameter:(Lnet/minecraft/util/context/ContextKey;Ljava/lang/Object;)Lnet/minecraft/util/context/ContextMap$Builder;");
        }

        public ContextMap create(ContextKeySet paramSet) {
            throw Unimplemented.forMember("net/minecraft/util/context/ContextMap$Builder.create:(Lnet/minecraft/util/context/ContextKeySet;)Lnet/minecraft/util/context/ContextMap;");
        }

        public Builder() {
        }
    }

    public ContextMap() {
    }
}
