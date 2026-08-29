package net.minecraft.client.gui.narration;

import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public interface NarrationElementOutput {

    default void add(NarratedElementType type, Component contents) {
        throw Unimplemented.forMember("net/minecraft/client/gui/narration/NarrationElementOutput.add:(Lnet/minecraft/client/gui/narration/NarratedElementType;Lnet/minecraft/network/chat/Component;)V");
    }

    default void add(NarratedElementType type, String contents) {
        throw Unimplemented.forMember("net/minecraft/client/gui/narration/NarrationElementOutput.add:(Lnet/minecraft/client/gui/narration/NarratedElementType;Ljava/lang/String;)V");
    }

    default void add(NarratedElementType type, Component... contents) {
        throw Unimplemented.forMember("net/minecraft/client/gui/narration/NarrationElementOutput.add:(Lnet/minecraft/client/gui/narration/NarratedElementType;[Lnet/minecraft/network/chat/Component;)V");
    }

    void add(final NarratedElementType type, final NarrationThunk<?> contents);

    NarrationElementOutput nest();
}
