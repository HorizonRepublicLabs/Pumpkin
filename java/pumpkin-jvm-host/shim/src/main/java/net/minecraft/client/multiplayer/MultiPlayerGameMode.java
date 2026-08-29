package net.minecraft.client.multiplayer;

import net.minecraft.client.Minecraft;
import dev.pumpkin.shim.Unimplemented;

public class MultiPlayerGameMode {

    public MultiPlayerGameMode(Minecraft minecraft, ClientPacketListener connection) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/MultiPlayerGameMode.<init>:(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/multiplayer/ClientPacketListener;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/MultiPlayerGameMode.tick:()V");
    }

    protected MultiPlayerGameMode() {
    }
}
