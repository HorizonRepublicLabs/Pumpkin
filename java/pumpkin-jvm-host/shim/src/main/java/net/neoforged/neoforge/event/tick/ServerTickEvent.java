package net.neoforged.neoforge.event.tick;

import java.util.function.BooleanSupplier;
import net.minecraft.server.MinecraftServer;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public abstract class ServerTickEvent extends Event {

    protected ServerTickEvent(BooleanSupplier hasTime, MinecraftServer server) {
    }

    public MinecraftServer getServer() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/tick/ServerTickEvent.getServer:()Lnet/minecraft/server/MinecraftServer;");
    }

    public static class Pre extends ServerTickEvent {

        public Pre(BooleanSupplier haveTime, MinecraftServer server) {
        }

        public Pre() {
        }
    }

    public static class Post extends ServerTickEvent {

        public Post(BooleanSupplier haveTime, MinecraftServer server) {
        }

        public Post() {
        }
    }

    public ServerTickEvent() {
    }
}
