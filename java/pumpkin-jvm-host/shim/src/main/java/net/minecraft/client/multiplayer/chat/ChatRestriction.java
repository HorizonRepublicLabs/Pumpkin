package net.minecraft.client.multiplayer.chat;

import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.permissions.Permission;
import dev.pumpkin.shim.Unimplemented;

public enum ChatRestriction {

    CHAT_AND_COMMANDS_DISABLED_BY_OPTIONS {

        public void modifyPermissions(Set<Permission> permissionSet) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/chat/ChatRestriction$CHAT_AND_COMMANDS_DISABLED_BY_OPTIONS.modifyPermissions:()");
        }
    }
    , CHAT_DISABLED_BY_OPTIONS {

        public void modifyPermissions(Set<Permission> permissionSet) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/chat/ChatRestriction$CHAT_DISABLED_BY_OPTIONS.modifyPermissions:()");
        }
    }
    , DISABLED_BY_LAUNCHER {

        public void modifyPermissions(Set<Permission> permissionSet) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/chat/ChatRestriction$DISABLED_BY_LAUNCHER.modifyPermissions:()");
        }
    }
    , DISABLED_BY_PROFILE {

        public void modifyPermissions(Set<Permission> permissionSet) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/chat/ChatRestriction$DISABLED_BY_PROFILE.modifyPermissions:()");
        }
    }
    ;

    public Optional<ChatRestriction.Action> action() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/chat/ChatRestriction.action:()Ljava/util/Optional;");
    }

    public abstract void modifyPermissions(Set<Permission> permissionSet);

    public record Action(Component title, BiConsumer<Minecraft, Screen> runnable) {
    }
}
