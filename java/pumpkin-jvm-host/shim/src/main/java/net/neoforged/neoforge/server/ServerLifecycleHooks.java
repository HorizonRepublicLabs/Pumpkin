package net.neoforged.neoforge.server;

import net.minecraft.server.MinecraftServer;
import dev.pumpkin.shim.Unimplemented;

public class ServerLifecycleHooks {

    public static MinecraftServer getCurrentServer() {
        throw Unimplemented.forMember("net/neoforged/neoforge/server/ServerLifecycleHooks.getCurrentServer:()Lnet/minecraft/server/MinecraftServer;");
    }

    public ServerLifecycleHooks() {
    }
}
