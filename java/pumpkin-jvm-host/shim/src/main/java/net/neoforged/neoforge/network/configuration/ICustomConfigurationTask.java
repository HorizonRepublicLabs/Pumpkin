package net.neoforged.neoforge.network.configuration;

import java.util.function.Consumer;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.network.ConfigurationTask;
import dev.pumpkin.shim.Unimplemented;

public interface ICustomConfigurationTask extends ConfigurationTask {

    void run(Consumer<CustomPacketPayload> sender);

    default void start(Consumer<Packet<?>> sender) {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/configuration/ICustomConfigurationTask.start:(Ljava/util/function/Consumer;)V");
    }
}
