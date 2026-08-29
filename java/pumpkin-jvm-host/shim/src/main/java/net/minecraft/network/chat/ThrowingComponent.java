package net.minecraft.network.chat;

import dev.pumpkin.shim.Unimplemented;

public class ThrowingComponent extends Exception {

    public ThrowingComponent(Component component) {
    }

    public ThrowingComponent(Component component, Throwable cause) {
    }

    public Component getComponent() {
        throw Unimplemented.forMember("net/minecraft/network/chat/ThrowingComponent.getComponent:()Lnet/minecraft/network/chat/Component;");
    }

    public ThrowingComponent() {
    }
}
