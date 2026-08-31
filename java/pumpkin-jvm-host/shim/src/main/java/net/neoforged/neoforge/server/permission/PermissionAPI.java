package net.neoforged.neoforge.server.permission;

import java.util.UUID;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.server.permission.nodes.PermissionDynamicContext;
import net.neoforged.neoforge.server.permission.nodes.PermissionNode;
import dev.pumpkin.shim.Unimplemented;

public final class PermissionAPI {

    protected PermissionAPI() {
    }

    public static <T> T getPermission(ServerPlayer player, PermissionNode<T> node, PermissionDynamicContext<?>... context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/server/permission/PermissionAPI.getPermission:(Lnet/minecraft/server/level/ServerPlayer;Lnet/neoforged/neoforge/server/permission/nodes/PermissionNode;[Lnet/neoforged/neoforge/server/permission/nodes/PermissionDynamicContext;)Ljava/lang/Object;");
    }

    public static <T> T getOfflinePermission(UUID player, PermissionNode<T> node, PermissionDynamicContext<?>... context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/server/permission/PermissionAPI.getOfflinePermission:(Ljava/util/UUID;Lnet/neoforged/neoforge/server/permission/nodes/PermissionNode;[Lnet/neoforged/neoforge/server/permission/nodes/PermissionDynamicContext;)Ljava/lang/Object;");
    }
}
