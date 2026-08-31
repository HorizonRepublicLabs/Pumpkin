package net.minecraft.network.chat;

import java.util.List;
import java.util.function.UnaryOperator;
import net.minecraft.ChatFormatting;
import net.minecraft.util.FormattedCharSequence;
import dev.pumpkin.shim.Unimplemented;

public final class MutableComponent implements Component {

    // Pumpkin divergence: the text this component carries. Enough for registration-time
    // titles and tooltips; styling still throws.
    private String pumpkinText = "";

    // Pumpkin divergence: the style is data the component carries; nothing renders it
    // server-side, but mods compose and re-read it while building names.
    private Style pumpkinStyle = Style.EMPTY;

    public static MutableComponent pumpkinOf(String text) {
        MutableComponent component = new MutableComponent();
        component.pumpkinText = text;
        return component;
    }

    public String pumpkinText() {
        return pumpkinText;
    }

    MutableComponent(ComponentContents contents, List<Component> siblings, Style style) {
    }

    public static MutableComponent create(ComponentContents contents) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.create:(Lnet/minecraft/network/chat/ComponentContents;)Lnet/minecraft/network/chat/MutableComponent;");
    }

    public ComponentContents getContents() {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.getContents:()Lnet/minecraft/network/chat/ComponentContents;");
    }

    public List<Component> getSiblings() {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.getSiblings:()Ljava/util/List;");
    }

    public MutableComponent setStyle(Style style) {
        this.pumpkinStyle = style;
        return this;
    }

    public Style getStyle() {
        return pumpkinStyle;
    }

    // Pumpkin divergence: real body.

    public MutableComponent append(String text) {

        pumpkinText = pumpkinText + text;

        return this;

    }

    // Pumpkin divergence: appends the sibling's text; the sibling's own style is
    // presentation the flat text cannot carry -- dropped, not misread.
    public MutableComponent append(Component component) {
        if (component instanceof MutableComponent mutable) {
            pumpkinText = pumpkinText + mutable.pumpkinText;
            return this;
        }
        pumpkinText = pumpkinText + component.getString();
        return this;
    }

    public MutableComponent withStyle(UnaryOperator<Style> updater) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.withStyle:(Ljava/util/function/UnaryOperator;)Lnet/minecraft/network/chat/MutableComponent;");
    }

    // Pumpkin divergence: vanilla applies the patch only where this style is unset;
    // with color the sole style fact Pumpkin stores, that is what this implements.
    public MutableComponent withStyle(Style patch) {
        if (pumpkinStyle.getColor() == null && patch.getColor() != null) {
            pumpkinStyle = pumpkinStyle.withColor(patch.getColor());
        }
        return this;
    }

    public MutableComponent withStyle(ChatFormatting... formats) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.withStyle:([Lnet/minecraft/ChatFormatting;)Lnet/minecraft/network/chat/MutableComponent;");
    }

    public MutableComponent withStyle(ChatFormatting format) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.withStyle:(Lnet/minecraft/ChatFormatting;)Lnet/minecraft/network/chat/MutableComponent;");
    }

    public MutableComponent withColor(int color) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.withColor:(I)Lnet/minecraft/network/chat/MutableComponent;");
    }

    public MutableComponent withColor(TextColor color) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.withColor:(Lnet/minecraft/network/chat/TextColor;)Lnet/minecraft/network/chat/MutableComponent;");
    }

    public FormattedCharSequence getVisualOrderText() {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.getVisualOrderText:()Lnet/minecraft/util/FormattedCharSequence;");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.toString:()Ljava/lang/String;");
    }

    public MutableComponent() {
    }
}
