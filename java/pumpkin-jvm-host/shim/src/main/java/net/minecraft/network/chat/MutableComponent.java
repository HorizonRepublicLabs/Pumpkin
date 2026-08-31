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
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.setStyle:(Lnet/minecraft/network/chat/Style;)Lnet/minecraft/network/chat/MutableComponent;");
    }

    public Style getStyle() {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.getStyle:()Lnet/minecraft/network/chat/Style;");
    }

    // Pumpkin divergence: real body.

    public MutableComponent append(String text) {

        pumpkinText = pumpkinText + text;

        return this;

    }

    public MutableComponent append(Component component) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.append:(Lnet/minecraft/network/chat/Component;)Lnet/minecraft/network/chat/MutableComponent;");
    }

    public MutableComponent withStyle(UnaryOperator<Style> updater) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.withStyle:(Ljava/util/function/UnaryOperator;)Lnet/minecraft/network/chat/MutableComponent;");
    }

    public MutableComponent withStyle(Style patch) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.withStyle:(Lnet/minecraft/network/chat/Style;)Lnet/minecraft/network/chat/MutableComponent;");
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
