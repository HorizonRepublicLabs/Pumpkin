package net.neoforged.neoforge.server.permission.nodes;

import java.util.UUID;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import dev.pumpkin.shim.Unimplemented;

public final class PermissionNode<T> {

    public PermissionNode(Identifier nodeName, PermissionType<T> type, PermissionResolver<T> defaultResolver, PermissionDynamicContextKey... dynamics) {
    }

    public PermissionNode(String modID, String nodeName, PermissionType<T> type, PermissionResolver<T> defaultResolver, PermissionDynamicContextKey... dynamics) {
    }

    private PermissionNode(String nodeName, PermissionType<T> type, PermissionResolver<T> defaultResolver, PermissionDynamicContextKey... dynamics) {
    }

    public String getNodeName() {
        throw Unimplemented.forMember("net/neoforged/neoforge/server/permission/nodes/PermissionNode.getNodeName:()Ljava/lang/String;");
    }

    public PermissionType<T> getType() {
        throw Unimplemented.forMember("net/neoforged/neoforge/server/permission/nodes/PermissionNode.getType:()Lnet/neoforged/neoforge/server/permission/nodes/PermissionType;");
    }

    public Component getDescription() {
        throw Unimplemented.forMember("net/neoforged/neoforge/server/permission/nodes/PermissionNode.getDescription:()Lnet/minecraft/network/chat/Component;");
    }

    public interface PermissionResolver<T> {

        T resolve(ServerPlayer player, UUID playerUUID, PermissionDynamicContext<?>... context);
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/neoforged/neoforge/server/permission/nodes/PermissionNode.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/neoforged/neoforge/server/permission/nodes/PermissionNode.hashCode:()I");
    }

    public PermissionNode() {
    }
}
