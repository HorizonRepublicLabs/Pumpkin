package net.minecraft.network.chat;

import java.util.Optional;

public interface FormattedText {

    <T> Optional<T> visit(final FormattedText.ContentConsumer<T> output);

    <T> Optional<T> visit(final FormattedText.StyledContentConsumer<T> output, final Style parentStyle);

    interface ContentConsumer<T> {

        Optional<T> accept(final String contents);
    }

    interface StyledContentConsumer<T> {

        Optional<T> accept(final Style style, final String contents);
    }
}
