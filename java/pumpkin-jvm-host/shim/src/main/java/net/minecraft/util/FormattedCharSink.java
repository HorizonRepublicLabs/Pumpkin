package net.minecraft.util;

import net.minecraft.network.chat.Style;

public interface FormattedCharSink {

    boolean accept(int position, Style style, int codepoint);
}
