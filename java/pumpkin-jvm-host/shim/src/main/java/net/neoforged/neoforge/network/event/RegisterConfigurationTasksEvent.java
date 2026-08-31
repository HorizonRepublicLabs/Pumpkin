package net.neoforged.neoforge.network.event;

import net.minecraft.network.protocol.configuration.ServerConfigurationPacketListener;
import net.minecraft.server.network.ConfigurationTask;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import dev.pumpkin.shim.Unimplemented;

public class RegisterConfigurationTasksEvent extends Event implements IModBusEvent {

    public RegisterConfigurationTasksEvent(ServerConfigurationPacketListener listener) {
    }

    public void register(ConfigurationTask task) {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/event/RegisterConfigurationTasksEvent.register:(Lnet/minecraft/server/network/ConfigurationTask;)V");
    }

    public ServerConfigurationPacketListener getListener() {
        throw Unimplemented.forMember("net/neoforged/neoforge/network/event/RegisterConfigurationTasksEvent.getListener:()Lnet/minecraft/network/protocol/configuration/ServerConfigurationPacketListener;");
    }

    public RegisterConfigurationTasksEvent() {
    }
}
