package net.minecraft.gametest.framework;

import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public abstract class GameTestException extends RuntimeException {

    public GameTestException(String message) {
        throw Unimplemented.forMember("net/minecraft/gametest/framework/GameTestException.<init>:(Ljava/lang/String;)V");
    }

    public abstract Component getDescription();

    public GameTestException() {
    }
}
