package net.minecraft.network.chat;

import com.mojang.serialization.Codec;
import dev.pumpkin.shim.Unimplemented;

public final class TextColor {

    public static final Codec<TextColor> CODEC =
            dev.pumpkin.shim.Stubs.throwingCodec("net.minecraft.network.chat.TextColor.CODEC");

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
        return o instanceof TextColor other && other.pumpkinValue == pumpkinValue;
    }

    public int hashCode() {
        return Integer.hashCode(pumpkinValue);
    }

    public String toString() {
        return String.format("#%06X", pumpkinValue);
    }

    // Pumpkin divergence: vanilla body -- wrap the rgb.
    public static TextColor fromRgb(int rgb) {
        return new TextColor(rgb);
    }

    public TextColor() {
    }
}
