package net.minecraft.network.chat;

import net.minecraft.ChatFormatting;
import dev.pumpkin.shim.Unimplemented;

public final class Style {

    public static final Style EMPTY = null;

    private Style(TextColor color, Integer shadowColor, Boolean bold, Boolean italic, Boolean underlined, Boolean strikethrough, Boolean obfuscated, ClickEvent clickEvent, HoverEvent hoverEvent, String insertion, FontDescription font) {
    }

    public TextColor getColor() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.getColor:()Lnet/minecraft/network/chat/TextColor;");
    }

    public boolean isBold() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.isBold:()Z");
    }

    public boolean isItalic() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.isItalic:()Z");
    }

    public boolean isStrikethrough() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.isStrikethrough:()Z");
    }

    public boolean isUnderlined() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.isUnderlined:()Z");
    }

    public boolean isObfuscated() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.isObfuscated:()Z");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.isEmpty:()Z");
    }

    public ClickEvent getClickEvent() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.getClickEvent:()Lnet/minecraft/network/chat/ClickEvent;");
    }

    public HoverEvent getHoverEvent() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.getHoverEvent:()Lnet/minecraft/network/chat/HoverEvent;");
    }

    public Style withColor(TextColor color) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.withColor:(Lnet/minecraft/network/chat/TextColor;)Lnet/minecraft/network/chat/Style;");
    }

    public Style withColor(ChatFormatting color) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.withColor:(Lnet/minecraft/ChatFormatting;)Lnet/minecraft/network/chat/Style;");
    }

    public Style withColor(int color) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.withColor:(I)Lnet/minecraft/network/chat/Style;");
    }

    public Style withClickEvent(ClickEvent clickEvent) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.withClickEvent:(Lnet/minecraft/network/chat/ClickEvent;)Lnet/minecraft/network/chat/Style;");
    }

    public Style withHoverEvent(HoverEvent hoverEvent) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.withHoverEvent:(Lnet/minecraft/network/chat/HoverEvent;)Lnet/minecraft/network/chat/Style;");
    }

    public Style applyFormat(ChatFormatting format) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.applyFormat:(Lnet/minecraft/ChatFormatting;)Lnet/minecraft/network/chat/Style;");
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
