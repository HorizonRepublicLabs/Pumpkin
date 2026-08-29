package net.minecraft.network.chat;

import com.mojang.brigadier.Message;
import java.util.List;
import java.util.Optional;
import net.minecraft.util.FormattedCharSequence;
import dev.pumpkin.shim.Unimplemented;

public interface Component extends Message, FormattedText {

    Style getStyle();

    ComponentContents getContents();

    default String getString() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Component.getString:()Ljava/lang/String;");
    }

    default String getString(int limit) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Component.getString:(I)Ljava/lang/String;");
    }

    List<Component> getSiblings();

    default MutableComponent copy() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Component.copy:()Lnet/minecraft/network/chat/MutableComponent;");
    }

    FormattedCharSequence getVisualOrderText();

    default <T> Optional<T> visit(FormattedText.StyledContentConsumer<T> output, Style parentStyle) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Component.visit:(Lnet/minecraft/network/chat/FormattedText$StyledContentConsumer;Lnet/minecraft/network/chat/Style;)Ljava/util/Optional;");
    }

    default <T> Optional<T> visit(FormattedText.ContentConsumer<T> output) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Component.visit:(Lnet/minecraft/network/chat/FormattedText$ContentConsumer;)Ljava/util/Optional;");
    }

    default boolean contains(Component other) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Component.contains:(Lnet/minecraft/network/chat/Component;)Z");
    }

    static MutableComponent literal(String text) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Component.literal:(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;");
    }

    static MutableComponent translatable(String key) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Component.translatable:(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;");
    }

    static MutableComponent translatable(String key, Object... args) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Component.translatable:(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;");
    }

    static MutableComponent empty() {
        throw Unimplemented.forMember("net/minecraft/network/chat/Component.empty:()Lnet/minecraft/network/chat/MutableComponent;");
    }
}
