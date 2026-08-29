package net.minecraft.client.multiplayer.chat;

import net.minecraft.network.chat.Component;

public record GuiMessageTag(int indicatorColor, GuiMessageTag.Icon icon, Component text, String logTag) {

    public enum Icon {

        CHAT_MODIFIED
    }
}
