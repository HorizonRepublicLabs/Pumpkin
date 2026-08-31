package net.neoforged.neoforge.server.permission.nodes;

import dev.pumpkin.shim.Unimplemented;

public final class PermissionDynamicContext<T> {

    PermissionDynamicContext(PermissionDynamicContextKey<T> dynamic, T value) {
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/neoforged/neoforge/server/permission/nodes/PermissionDynamicContext.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/neoforged/neoforge/server/permission/nodes/PermissionDynamicContext.hashCode:()I");
    }

    public PermissionDynamicContext() {
    }
}
