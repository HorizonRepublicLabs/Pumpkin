package net.minecraft.network.chat;

import net.minecraft.ChatFormatting;
import dev.pumpkin.shim.Unimplemented;

public final class Style {

    // Pumpkin divergence: a style really is just data -- the ctor stores it and the
    // getters answer from it.
    final TextColor pumpkinColor;
    final Integer pumpkinShadowColor;
    final Boolean pumpkinBold;
    final Boolean pumpkinItalic;
    final Boolean pumpkinUnderlined;
    final Boolean pumpkinStrikethrough;
    final Boolean pumpkinObfuscated;
    final ClickEvent pumpkinClickEvent;
    final HoverEvent pumpkinHoverEvent;
    final String pumpkinInsertion;
    final FontDescription pumpkinFont;

    public static final Style EMPTY = new Style(null, null, null, null, null, null, null, null, null, null, null);

    private Style(TextColor color, Integer shadowColor, Boolean bold, Boolean italic, Boolean underlined, Boolean strikethrough, Boolean obfuscated, ClickEvent clickEvent, HoverEvent hoverEvent, String insertion, FontDescription font) {
        this.pumpkinColor = color;
        this.pumpkinShadowColor = shadowColor;
        this.pumpkinBold = bold;
        this.pumpkinItalic = italic;
        this.pumpkinUnderlined = underlined;
        this.pumpkinStrikethrough = strikethrough;
        this.pumpkinObfuscated = obfuscated;
        this.pumpkinClickEvent = clickEvent;
        this.pumpkinHoverEvent = hoverEvent;
        this.pumpkinInsertion = insertion;
        this.pumpkinFont = font;
    }

    public TextColor getColor() {
        return pumpkinColor;
    }

    public boolean isBold() {
        return pumpkinBold == Boolean.TRUE;
    }

    public boolean isItalic() {
        return pumpkinItalic == Boolean.TRUE;
    }

    public boolean isStrikethrough() {
        return pumpkinStrikethrough == Boolean.TRUE;
    }

    public boolean isUnderlined() {
        return pumpkinUnderlined == Boolean.TRUE;
    }

    public boolean isObfuscated() {
        return pumpkinObfuscated == Boolean.TRUE;
    }

    public boolean isEmpty() {
        return this.equals(EMPTY);
    }

    public ClickEvent getClickEvent() {
        return pumpkinClickEvent;
    }

    public HoverEvent getHoverEvent() {
        return pumpkinHoverEvent;
    }

    public Style withColor(TextColor color) {
        return new Style(color, pumpkinShadowColor, pumpkinBold, pumpkinItalic, pumpkinUnderlined, pumpkinStrikethrough, pumpkinObfuscated, pumpkinClickEvent, pumpkinHoverEvent, pumpkinInsertion, pumpkinFont);
    }

    public Style withColor(ChatFormatting color) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.withColor:(Lnet/minecraft/ChatFormatting;)Lnet/minecraft/network/chat/Style;");
    }

    public Style withColor(int color) {
        return withColor(TextColor.fromRgb(color));
    }

    public Style withClickEvent(ClickEvent clickEvent) {
        return new Style(pumpkinColor, pumpkinShadowColor, pumpkinBold, pumpkinItalic, pumpkinUnderlined, pumpkinStrikethrough, pumpkinObfuscated, clickEvent, pumpkinHoverEvent, pumpkinInsertion, pumpkinFont);
    }

    public Style withHoverEvent(HoverEvent hoverEvent) {
        return new Style(pumpkinColor, pumpkinShadowColor, pumpkinBold, pumpkinItalic, pumpkinUnderlined, pumpkinStrikethrough, pumpkinObfuscated, pumpkinClickEvent, hoverEvent, pumpkinInsertion, pumpkinFont);
    }

    public Style applyFormat(ChatFormatting format) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.applyFormat:(Lnet/minecraft/ChatFormatting;)Lnet/minecraft/network/chat/Style;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Style.toString:()Ljava/lang/String;");
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Style other)) {
            return false;
        }
        return java.util.Objects.equals(pumpkinColor, other.pumpkinColor)
            && java.util.Objects.equals(pumpkinShadowColor, other.pumpkinShadowColor)
            && java.util.Objects.equals(pumpkinBold, other.pumpkinBold)
            && java.util.Objects.equals(pumpkinItalic, other.pumpkinItalic)
            && java.util.Objects.equals(pumpkinUnderlined, other.pumpkinUnderlined)
            && java.util.Objects.equals(pumpkinStrikethrough, other.pumpkinStrikethrough)
            && java.util.Objects.equals(pumpkinObfuscated, other.pumpkinObfuscated)
            && java.util.Objects.equals(pumpkinClickEvent, other.pumpkinClickEvent)
            && java.util.Objects.equals(pumpkinHoverEvent, other.pumpkinHoverEvent)
            && java.util.Objects.equals(pumpkinInsertion, other.pumpkinInsertion)
            && java.util.Objects.equals(pumpkinFont, other.pumpkinFont);
    }

    public int hashCode() {
        return java.util.Objects.hash(pumpkinColor, pumpkinShadowColor, pumpkinBold, pumpkinItalic, pumpkinUnderlined, pumpkinStrikethrough, pumpkinObfuscated, pumpkinClickEvent, pumpkinHoverEvent, pumpkinInsertion, pumpkinFont);
    }

    public static class Serializer {

        public Serializer() {
        }
    }

    public Style() {
        this(null, null, null, null, null, null, null, null, null, null, null);
    }
}
