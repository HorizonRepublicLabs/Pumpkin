package net.minecraft.client.gui.narration;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import dev.pumpkin.shim.Unimplemented;

public class NarrationThunk<T> {

    private NarrationThunk(T contents, BiConsumer<Consumer<String>, T> converter) {
        throw Unimplemented.forMember("net/minecraft/client/gui/narration/NarrationThunk.<init>:(Ljava/lang/Object;Ljava/util/function/BiConsumer;)V");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/client/gui/narration/NarrationThunk.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/client/gui/narration/NarrationThunk.hashCode:()I");
    }

    protected NarrationThunk() {
    }
}
