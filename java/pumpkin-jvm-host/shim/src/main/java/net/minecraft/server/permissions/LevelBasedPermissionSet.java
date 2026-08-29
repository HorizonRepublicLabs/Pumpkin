package net.minecraft.server.permissions;

import dev.pumpkin.shim.Unimplemented;

public interface LevelBasedPermissionSet extends PermissionSet {

    PermissionLevel level();

    default boolean hasPermission(Permission permission) {
        throw Unimplemented.forMember("net/minecraft/server/permissions/LevelBasedPermissionSet.hasPermission:(Lnet/minecraft/server/permissions/Permission;)Z");
    }

    default PermissionSet union(PermissionSet other) {
        throw Unimplemented.forMember("net/minecraft/server/permissions/LevelBasedPermissionSet.union:(Lnet/minecraft/server/permissions/PermissionSet;)Lnet/minecraft/server/permissions/PermissionSet;");
    }

    private static LevelBasedPermissionSet create(PermissionLevel level) {
        throw Unimplemented.forMember("net/minecraft/server/permissions/LevelBasedPermissionSet.create:(Lnet/minecraft/server/permissions/PermissionLevel;)Lnet/minecraft/server/permissions/LevelBasedPermissionSet;");
    }
}
