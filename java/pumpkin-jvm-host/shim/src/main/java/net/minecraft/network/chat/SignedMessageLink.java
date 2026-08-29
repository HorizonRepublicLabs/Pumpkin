package net.minecraft.network.chat;

import java.util.UUID;

public record SignedMessageLink(int index, UUID sender, UUID sessionId) {
}
