package net.minecraft.network.chat;

import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public interface FormattedText {

    <T> Optional<T> visit(final FormattedText.ContentConsumer<T> output);

    <T> Optional<T> visit(final FormattedText.StyledContentConsumer<T> output, final Style parentStyle);

    default String getString() {
        throw Unimplemented.forMember("net/minecraft/network/chat/FormattedText.getString:()Ljava/lang/String;");
    }

    interface ContentConsumer<T> {

        Optional<T> accept(final String contents);
    }

    interface StyledContentConsumer<T> {

        Optional<T> accept(final Style style, final String contents);
    }
}
