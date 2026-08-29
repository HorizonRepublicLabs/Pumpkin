package net.minecraft.gametest.framework;

public record RetryOptions(int numberOfTries, boolean haltOnFailure) {
}
