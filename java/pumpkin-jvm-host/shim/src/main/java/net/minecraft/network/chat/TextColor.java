package net.minecraft.network.chat;

import com.mojang.serialization.Codec;
import dev.pumpkin.shim.Unimplemented;

public final class TextColor {

    public static final Codec<TextColor> CODEC = null;

    // Pumpkin divergence: a color really carries its value.
    private int pumpkinValue;

    private TextColor(int value, String name) {
        this.pumpkinValue = value;
    }

    private TextColor(int value) {
        this.pumpkinValue = value;
    }

    public int getValue() {
        return pumpkinValue;
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

    // Pumpkin divergence: vanilla body -- wrap the rgb.
    public static TextColor fromRgb(int rgb) {
        return new TextColor(rgb);
    }

    public TextColor() {
    }
}
