package net.minecraft.network.chat;

import dev.pumpkin.shim.Unimplemented;

public class ThrowingComponent extends Exception {

    public ThrowingComponent(Component component) {
        throw Unimplemented.forMember("net/minecraft/network/chat/ThrowingComponent.<init>:(Lnet/minecraft/network/chat/Component;)V");
    }

    public ThrowingComponent(Component component, Throwable cause) {
        throw Unimplemented.forMember("net/minecraft/network/chat/ThrowingComponent.<init>:(Lnet/minecraft/network/chat/Component;Ljava/lang/Throwable;)V");
    }

    public Component getComponent() {
        throw Unimplemented.forMember("net/minecraft/network/chat/ThrowingComponent.getComponent:()Lnet/minecraft/network/chat/Component;");
    }

    protected ThrowingComponent() {
    }
}
