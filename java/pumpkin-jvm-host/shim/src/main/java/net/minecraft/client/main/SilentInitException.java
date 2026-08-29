package net.minecraft.client.main;

import dev.pumpkin.shim.Unimplemented;

public class SilentInitException extends RuntimeException {

    public SilentInitException(String message) {
        throw Unimplemented.forMember("net/minecraft/client/main/SilentInitException.<init>:(Ljava/lang/String;)V");
    }

    public SilentInitException(String message, Throwable cause) {
        throw Unimplemented.forMember("net/minecraft/client/main/SilentInitException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V");
    }

    protected SilentInitException() {
    }
}
