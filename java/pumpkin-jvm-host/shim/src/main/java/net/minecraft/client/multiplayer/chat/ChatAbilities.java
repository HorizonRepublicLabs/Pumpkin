package net.minecraft.client.multiplayer.chat;

import java.util.Set;
import net.minecraft.server.permissions.PermissionSet;
import dev.pumpkin.shim.Unimplemented;

public class ChatAbilities {

    private ChatAbilities(Set<ChatRestriction> restrictionReasons) {
    }

    public PermissionSet permissions() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/chat/ChatAbilities.permissions:()Lnet/minecraft/server/permissions/PermissionSet;");
    }

    public static class Builder {

        public ChatAbilities build() {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/chat/ChatAbilities$Builder.build:()Lnet/minecraft/client/multiplayer/chat/ChatAbilities;");
        }

        public Builder() {
        }
    }

    public ChatAbilities() {
    }
}
