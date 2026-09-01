package net.neoforged.neoforge.server;

import net.minecraft.server.MinecraftServer;
import dev.pumpkin.shim.Unimplemented;

public class ServerLifecycleHooks {

    // Pumpkin divergence: the running server is the bridge's stand-in -- the same
    // instance the tick events carry.
    public static MinecraftServer getCurrentServer() {
        return dev.pumpkin.bridge.PumpkinMinecraftServer.pumpkinInstance();
    }

    public ServerLifecycleHooks() {
    }
}
