package net.minecraft.util.context;

import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class ContextKey<T> {

    public ContextKey(Identifier name) {
    }

    public Identifier name() {
        throw Unimplemented.forMember("net/minecraft/util/context/ContextKey.name:()Lnet/minecraft/resources/Identifier;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/util/context/ContextKey.toString:()Ljava/lang/String;");
    }

    public ContextKey() {
    }
}
