package net.neoforged.neoforge.event.tick;

import java.util.function.BooleanSupplier;
import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public abstract class ServerTickEvent extends Event {

    // Pumpkin divergence: the event carries what its constructor was given; a bare
    // constructor left it empty, and asking then still refuses.
    private MinecraftServer pumpkinServer;

    protected ServerTickEvent(BooleanSupplier hasTime, MinecraftServer server) {
        this.pumpkinServer = server;
    }

    public MinecraftServer getServer() {
        if (pumpkinServer == null) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/tick/ServerTickEvent.getServer:()Lnet/minecraft/server/MinecraftServer;");
        }
        return pumpkinServer;
    }

    public static class Pre extends ServerTickEvent {

        public Pre(BooleanSupplier haveTime, MinecraftServer server) {
            super(haveTime, server);
        }

        public Pre() {
        }
    }

    public static class Post extends ServerTickEvent {

        public Post(BooleanSupplier haveTime, MinecraftServer server) {
            super(haveTime, server);
        }

        public Post() {
        }
    }

    public ServerTickEvent() {
    }
}
