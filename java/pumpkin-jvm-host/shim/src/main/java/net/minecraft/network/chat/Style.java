package net.minecraft.network.chat;

import dev.pumpkin.shim.Unimplemented;

public final class Style {

    private Style(TextColor color, Integer shadowColor, Boolean bold, Boolean italic, Boolean underlined, Boolean strikethrough, Boolean obfuscated, ClickEvent clickEvent, HoverEvent hoverEvent, String insertion, FontDescription font) {
    }

    public TextColor getColor() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.getColor:()Lnet/minecraft/network/chat/TextColor;");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.isEmpty:()Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.toString:()Ljava/lang/String;");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.hashCode:()I");
    }

    public static class Serializer {

        public Serializer() {
        }
    }

    public Style() {
    }
}
