package net.minecraft.client.multiplayer;

import net.minecraft.client.Minecraft;
import dev.pumpkin.shim.Unimplemented;

public class MultiPlayerGameMode {

    public MultiPlayerGameMode(Minecraft minecraft, ClientPacketListener connection) {
    }

    public boolean canHurtPlayer() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/MultiPlayerGameMode.canHurtPlayer:()Z");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/MultiPlayerGameMode.tick:()V");
    }

    public MultiPlayerGameMode() {
    }
}
