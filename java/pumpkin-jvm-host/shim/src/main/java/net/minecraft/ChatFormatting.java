package net.minecraft;

import dev.pumpkin.shim.Unimplemented;

public enum ChatFormatting {

    BLACK,
    DARK_BLUE,
    DARK_GREEN,
    DARK_AQUA,
    DARK_RED,
    DARK_PURPLE,
    GOLD,
    GRAY,
    DARK_GRAY,
    BLUE,
    GREEN,
    AQUA,
    RED,
    LIGHT_PURPLE,
    YELLOW,
    WHITE,
    OBFUSCATED,
    BOLD,
    STRIKETHROUGH,
    UNDERLINE,
    ITALIC,
    RESET;

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/ChatFormatting.toString:()Ljava/lang/String;");
    }
}
