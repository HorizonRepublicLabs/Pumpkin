package net.minecraft.client.gui;

import net.minecraft.util.FormattedCharSequence;
import dev.pumpkin.shim.Unimplemented;

public enum TextAlignment {

    LEFT {

        public int calculateLeft(int anchor, int width) {
            throw Unimplemented.forMember("net/minecraft/client/gui/TextAlignment$LEFT.calculateLeft:()");
        }

        public int calculateLeft(int anchor, Font font, FormattedCharSequence text) {
            throw Unimplemented.forMember("net/minecraft/client/gui/TextAlignment$LEFT.calculateLeft:()");
        }
    }
    , CENTER {

        public int calculateLeft(int anchor, int width) {
            throw Unimplemented.forMember("net/minecraft/client/gui/TextAlignment$CENTER.calculateLeft:()");
        }
    }
    , RIGHT {

        public int calculateLeft(int anchor, int width) {
            throw Unimplemented.forMember("net/minecraft/client/gui/TextAlignment$RIGHT.calculateLeft:()");
        }
    }
    ;

    public abstract int calculateLeft(int anchor, int width);
}
