package net.minecraft.world.entity;

public interface PlayerRideableJumping extends PlayerRideable {

    void onPlayerJump(int jumpAmount);

    boolean canJump();

    void handleStartJump(int jumpScale);

    void handleStopJump();
}
