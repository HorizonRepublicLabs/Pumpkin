package net.neoforged.neoforge.event.server;

import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public abstract class ServerLifecycleEvent extends Event {

    public ServerLifecycleEvent(MinecraftServer server) {
    }

    public MinecraftServer getServer() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/server/ServerLifecycleEvent.getServer:()Lnet/minecraft/server/MinecraftServer;");
    }

    public ServerLifecycleEvent() {
    }
}
