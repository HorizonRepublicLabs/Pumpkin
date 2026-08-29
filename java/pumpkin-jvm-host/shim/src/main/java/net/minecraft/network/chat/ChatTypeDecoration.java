package net.minecraft.network.chat;

import java.util.List;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public record ChatTypeDecoration(String translationKey, List<ChatTypeDecoration.Parameter> parameters, Style style) {

    public enum Parameter implements StringRepresentable {

        SENDER, TARGET, CONTENT;

        public Component select(Component content, ChatType.Bound chatType) {
            throw Unimplemented.forMember("net/minecraft/network/chat/ChatTypeDecoration$Parameter.select:(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/ChatType$Bound;)Lnet/minecraft/network/chat/Component;");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/network/chat/ChatTypeDecoration$Parameter.getSerializedName:()Ljava/lang/String;");
        }

        public interface Selector {

            Component select(Component content, ChatType.Bound chatType);
        }
    }
}
