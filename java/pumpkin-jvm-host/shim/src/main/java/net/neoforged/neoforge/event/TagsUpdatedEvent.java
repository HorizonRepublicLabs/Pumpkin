package net.neoforged.neoforge.event;

import net.minecraft.core.RegistryAccess;
import net.minecraft.server.ReloadableServerResources;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public class TagsUpdatedEvent extends Event {

    protected TagsUpdatedEvent(RegistryAccess registries) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/TagsUpdatedEvent.<init>:(Lnet/minecraft/core/RegistryAccess;)V");
    }

    public boolean shouldUpdateStaticData() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/TagsUpdatedEvent.shouldUpdateStaticData:()Z");
    }

    public static final class ServerDataLoad extends TagsUpdatedEvent {

        public ServerDataLoad(ReloadableServerResources serverResources, RegistryAccess registries) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/TagsUpdatedEvent$ServerDataLoad.<init>:(Lnet/minecraft/server/ReloadableServerResources;Lnet/minecraft/core/RegistryAccess;)V");
        }

        public ServerDataLoad() {
        }
    }

    public static final class ClientPacketReceived extends TagsUpdatedEvent {

        public ClientPacketReceived(RegistryAccess registries, boolean isIntegratedServerConnection) {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/TagsUpdatedEvent$ClientPacketReceived.<init>:(Lnet/minecraft/core/RegistryAccess;Z)V");
        }

        public UpdateCause getUpdateCause() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/TagsUpdatedEvent$ClientPacketReceived.getUpdateCause:()Lnet/neoforged/neoforge/event/TagsUpdatedEvent$UpdateCause;");
        }

        public boolean shouldUpdateStaticData() {
            throw Unimplemented.forMember("net/neoforged/neoforge/event/TagsUpdatedEvent$ClientPacketReceived.shouldUpdateStaticData:()Z");
        }

        public ClientPacketReceived() {
        }
    }

    public enum UpdateCause {

        SERVER_DATA_LOAD, CLIENT_PACKET_RECEIVED
    }

    public TagsUpdatedEvent() {
    }
}
