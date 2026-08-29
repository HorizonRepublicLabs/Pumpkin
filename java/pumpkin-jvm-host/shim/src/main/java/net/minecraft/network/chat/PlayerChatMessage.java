package net.minecraft.network.chat;

public record PlayerChatMessage(SignedMessageLink link, MessageSignature signature, SignedMessageBody signedBody, Component unsignedContent, FilterMask filterMask) {
}
