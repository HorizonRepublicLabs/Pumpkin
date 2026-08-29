package net.minecraft.network.chat;

import dev.pumpkin.shim.Unimplemented;

public final class TextColor {

    private TextColor(int value, String name) {
        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.<init>:(ILjava/lang/String;)V");
    }

    private TextColor(int value) {
        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.<init>:(I)V");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.toString:()Ljava/lang/String;");
    }

    public TextColor() {
    }
}
