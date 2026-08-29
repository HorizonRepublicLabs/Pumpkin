package net.minecraft.util.context;

import java.util.Map;
import dev.pumpkin.shim.Unimplemented;

public class ContextMap {

    private ContextMap(Map<ContextKey<?>, Object> params) {
    }

    public static class Builder {

        public ContextMap create(ContextKeySet paramSet) {
            throw Unimplemented.forMember("net/minecraft/util/context/ContextMap$Builder.create:(Lnet/minecraft/util/context/ContextKeySet;)Lnet/minecraft/util/context/ContextMap;");
        }

        public Builder() {
        }
    }

    public ContextMap() {
    }
}
