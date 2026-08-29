package net.minecraft.core.component;

import dev.pumpkin.shim.Unimplemented;

public record TypedDataComponent<T>(DataComponentType<T> type, T value) {

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/core/component/TypedDataComponent.toString:()Ljava/lang/String;");
    }
}
