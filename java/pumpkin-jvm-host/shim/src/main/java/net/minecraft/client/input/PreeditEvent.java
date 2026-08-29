package net.minecraft.client.input;

import java.util.List;

public record PreeditEvent(String fullText, int caretPosition, List<String> blocks, int focusedBlock) {
}
