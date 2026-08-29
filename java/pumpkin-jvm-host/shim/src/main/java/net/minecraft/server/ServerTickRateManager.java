package net.minecraft.server;

import net.minecraft.world.TickRateManager;
import dev.pumpkin.shim.Unimplemented;

public class ServerTickRateManager extends TickRateManager {

    public ServerTickRateManager(MinecraftServer server) {
        throw Unimplemented.forMember("net/minecraft/server/ServerTickRateManager.<init>:(Lnet/minecraft/server/MinecraftServer;)V");
    }

    public void setFrozen(boolean frozen) {
        throw Unimplemented.forMember("net/minecraft/server/ServerTickRateManager.setFrozen:(Z)V");
    }

    public void setTickRate(float rate) {
        throw Unimplemented.forMember("net/minecraft/server/ServerTickRateManager.setTickRate:(F)V");
    }

    protected ServerTickRateManager() {
    }
}
