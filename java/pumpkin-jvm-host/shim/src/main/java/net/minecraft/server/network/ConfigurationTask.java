package net.minecraft.server.network;

import java.util.function.Consumer;
import net.minecraft.network.protocol.Packet;
import dev.pumpkin.shim.Unimplemented;

public interface ConfigurationTask {

    void start(Consumer<Packet<?>> connection);

    default boolean tick() {
        throw Unimplemented.forMember("net/minecraft/server/network/ConfigurationTask.tick:()Z");
    }

    ConfigurationTask.Type type();

    record Type(String id) {

        public Type(net.minecraft.resources.Identifier location) {
            this((String) null);
        }

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/server/network/ConfigurationTask$Type.toString:()Ljava/lang/String;");
        }
    }
}
