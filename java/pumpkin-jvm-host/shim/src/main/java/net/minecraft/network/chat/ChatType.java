package net.minecraft.network.chat;

import java.util.Optional;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public record ChatType(ChatTypeDecoration chat, ChatTypeDecoration narration) {

    private static ResourceKey<ChatType> create(String name) {
        throw Unimplemented.forMember("net/minecraft/network/chat/ChatType.create:(Ljava/lang/String;)Lnet/minecraft/resources/ResourceKey;");
    }

    public record Bound(Holder<ChatType> chatType, Component name, Optional<Component> targetName) {

        private Bound(Holder<ChatType> chatType, Component name) {
            this((Holder<ChatType>) null, (Component) null, (Optional<Component>) null);
        }

        public Component decorate(Component content) {
            throw Unimplemented.forMember("net/minecraft/network/chat/ChatType$Bound.decorate:(Lnet/minecraft/network/chat/Component;)Lnet/minecraft/network/chat/Component;");
        }
    }
}
