package net.minecraft.network.chat;

import com.mojang.brigadier.Message;
import java.util.List;
import java.util.Optional;
import net.minecraft.util.FormattedCharSequence;
import dev.pumpkin.shim.Unimplemented;

public interface Component extends Message, FormattedText {

    Style getStyle();

    ComponentContents getContents();
    // Pumpkin divergence: real where the component can answer, loud where it cannot.
    default String getString() {
        if (this instanceof MutableComponent mutable) {
            return mutable.pumpkinText();
        }
        throw Unimplemented.forMember("net/minecraft/network/chat/Component.getString:()Ljava/lang/String;");
    }

    default String getString(int limit) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Component.getString:(I)Ljava/lang/String;");
    }

    List<Component> getSiblings();

    // Pumpkin divergence: real for the text-carrying components Pumpkin builds;
    // anything else has no data to copy and fails loudly.
    default MutableComponent copy() {
        if (this instanceof MutableComponent mutable) {
            MutableComponent copy = MutableComponent.pumpkinOf(mutable.pumpkinText());
            copy.setStyle(mutable.getStyle());
            return copy;
        }
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

    // Pumpkin divergence: real bodies. A component is text; translation keys stay

    // keys, because the server has no language files and the client translates.

    static MutableComponent literal(String text) {

        return MutableComponent.pumpkinOf(text);

    }

    static MutableComponent translatable(String key) {

        return MutableComponent.pumpkinOf(key);

    }

    static MutableComponent translatable(String key, Object... args) {

        return MutableComponent.pumpkinOf(key);

    }

    static MutableComponent translatableWithFallback(String key, String fallback) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Component.translatableWithFallback:(Ljava/lang/String;Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;");
    }

    static MutableComponent translatableWithFallback(String key, String fallback, Object... args) {
        throw Unimplemented.forMember("net/minecraft/network/chat/Component.translatableWithFallback:(Ljava/lang/String;Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;");
    }

    static MutableComponent empty() {

        return MutableComponent.pumpkinOf("");

    }
}
