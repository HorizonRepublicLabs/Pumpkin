package net.minecraft.gametest.framework;

import net.minecraft.network.chat.Component;

public abstract class GameTestException extends RuntimeException {

    public GameTestException(String message) {
    }

    public abstract Component getDescription();

    public GameTestException() {
    }
}
