package net.minecraft.commands;

import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public class FunctionInstantiationException extends Exception {

    public FunctionInstantiationException(Component messageComponent) {
        throw Unimplemented.forMember("net/minecraft/commands/FunctionInstantiationException.<init>:(Lnet/minecraft/network/chat/Component;)V");
    }

    public Component messageComponent() {
        throw Unimplemented.forMember("net/minecraft/commands/FunctionInstantiationException.messageComponent:()Lnet/minecraft/network/chat/Component;");
    }

    protected FunctionInstantiationException() {
    }
}
