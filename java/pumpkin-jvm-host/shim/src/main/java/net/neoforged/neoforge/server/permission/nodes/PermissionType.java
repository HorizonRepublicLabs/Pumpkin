package net.neoforged.neoforge.server.permission.nodes;

import dev.pumpkin.shim.Unimplemented;

public final class PermissionType<T> {

    PermissionType(Class<T> typeToken, String typeName) {
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/neoforged/neoforge/server/permission/nodes/PermissionType.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/neoforged/neoforge/server/permission/nodes/PermissionType.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/server/permission/nodes/PermissionType.toString:()Ljava/lang/String;");
    }

    public PermissionType() {
    }
}
