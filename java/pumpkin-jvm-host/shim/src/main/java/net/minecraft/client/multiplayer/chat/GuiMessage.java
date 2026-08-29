package net.minecraft.client.multiplayer.chat;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MessageSignature;
import net.minecraft.util.FormattedCharSequence;

public record GuiMessage(int addedTime, Component content, MessageSignature signature, GuiMessageSource source, GuiMessageTag tag) {

    public record Line(GuiMessage parent, FormattedCharSequence content, boolean endOfEntry) {
    }
}
