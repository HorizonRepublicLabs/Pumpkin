package net.minecraft.network.chat;

import com.mojang.serialization.Codec;
import dev.pumpkin.shim.Unimplemented;

public final class TextColor {

    public static final Codec<TextColor> CODEC = null;

    private TextColor(int value, String name) {
    }

    private TextColor(int value) {
    }

    public int getValue() {
        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.getValue:()I");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.toString:()Ljava/lang/String;");
    }

    public static TextColor fromRgb(int rgb) {
        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.fromRgb:(I)Lnet/minecraft/network/chat/TextColor;");
    }

    public TextColor() {
    }
}
