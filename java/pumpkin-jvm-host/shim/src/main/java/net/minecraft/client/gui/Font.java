package net.minecraft.client.gui;

import java.util.List;
import net.minecraft.client.gui.font.glyphs.BakedGlyph;
import net.minecraft.client.gui.font.glyphs.EffectGlyph;
import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.network.chat.FontDescription;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.FormattedCharSink;
import net.neoforged.neoforge.client.extensions.IFontExtension;
import dev.pumpkin.shim.Unimplemented;

public class Font implements IFontExtension {

    public Font(Font.Provider provider) {
    }

    public int width(String str) {
        throw Unimplemented.forMember("net/minecraft/client/gui/Font.width:(Ljava/lang/String;)I");
    }

    public int width(FormattedText text) {
        throw Unimplemented.forMember("net/minecraft/client/gui/Font.width:(Lnet/minecraft/network/chat/FormattedText;)I");
    }

    public int width(FormattedCharSequence text) {
        throw Unimplemented.forMember("net/minecraft/client/gui/Font.width:(Lnet/minecraft/util/FormattedCharSequence;)I");
    }

    public List<FormattedCharSequence> split(FormattedText input, int maxWidth) {
        throw Unimplemented.forMember("net/minecraft/client/gui/Font.split:(Lnet/minecraft/network/chat/FormattedText;I)Ljava/util/List;");
    }

    public boolean isBidirectional() {
        throw Unimplemented.forMember("net/minecraft/client/gui/Font.isBidirectional:()Z");
    }

    public enum DisplayMode {

        NORMAL, SEE_THROUGH, POLYGON_OFFSET
    }

    public interface GlyphVisitor {
    }

    public interface PreparedText {

        void visit(Font.GlyphVisitor visitor);

        ScreenRectangle bounds();
    }

    private class PreparedTextBuilder implements Font.PreparedText, FormattedCharSink {

        public PreparedTextBuilder(float x, float y, int color, boolean drawShadow, boolean includeEmpty) {
        }

        public PreparedTextBuilder(float x, float y, int color, int backgroundColor, boolean drawShadow, boolean includeEmpty) {
        }

        public boolean accept(int position, Style style, int c) {
            throw Unimplemented.forMember("net/minecraft/client/gui/Font$PreparedTextBuilder.accept:(ILnet/minecraft/network/chat/Style;I)Z");
        }

        public boolean accept(int position, Style style, BakedGlyph glyph) {
            throw Unimplemented.forMember("net/minecraft/client/gui/Font$PreparedTextBuilder.accept:(ILnet/minecraft/network/chat/Style;Lnet/minecraft/client/gui/font/glyphs/BakedGlyph;)Z");
        }

        public void visit(Font.GlyphVisitor visitor) {
            throw Unimplemented.forMember("net/minecraft/client/gui/Font$PreparedTextBuilder.visit:(Lnet/minecraft/client/gui/Font$GlyphVisitor;)V");
        }

        public ScreenRectangle bounds() {
            throw Unimplemented.forMember("net/minecraft/client/gui/Font$PreparedTextBuilder.bounds:()Lnet/minecraft/client/gui/navigation/ScreenRectangle;");
        }

        protected PreparedTextBuilder() {
        }
    }

    public interface Provider {

        GlyphSource glyphs(FontDescription font);

        EffectGlyph effect();
    }

    public Font() {
    }
}
