package net.minecraft.network.chat;

import java.util.List;
import java.util.function.UnaryOperator;
import net.minecraft.ChatFormatting;
import net.minecraft.util.FormattedCharSequence;
import dev.pumpkin.shim.Unimplemented;

public final class MutableComponent implements Component {

    MutableComponent(ComponentContents contents, List<Component> siblings, Style style) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.<init>:(Lnet/minecraft/network/chat/ComponentContents;Ljava/util/List;Lnet/minecraft/network/chat/Style;)V");
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

    public Style getStyle() {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.getStyle:()Lnet/minecraft/network/chat/Style;");
    }

    public MutableComponent append(String text) {
        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.append:(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;");
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

    protected MutableComponent() {
    }
}
