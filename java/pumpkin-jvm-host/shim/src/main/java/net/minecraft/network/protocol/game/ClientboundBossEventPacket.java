package net.minecraft.network.protocol.game;

import java.util.UUID;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.BossEvent;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundBossEventPacket implements Packet<ClientGamePacketListener> {

    private ClientboundBossEventPacket(UUID id, ClientboundBossEventPacket.Operation operation) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket.<init>:(Ljava/util/UUID;Lnet/minecraft/network/protocol/game/ClientboundBossEventPacket$Operation;)V");
    }

    private ClientboundBossEventPacket(RegistryFriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket.<init>:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundBossEventPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    private static class AddOperation implements ClientboundBossEventPacket.Operation {

        private AddOperation(BossEvent event) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$AddOperation.<init>:(Lnet/minecraft/world/BossEvent;)V");
        }

        private AddOperation(RegistryFriendlyByteBuf input) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$AddOperation.<init>:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
        }

        public ClientboundBossEventPacket.OperationType getType() {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$AddOperation.getType:()Lnet/minecraft/network/protocol/game/ClientboundBossEventPacket$OperationType;");
        }

        public void dispatch(UUID id, ClientboundBossEventPacket.Handler handler) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$AddOperation.dispatch:(Ljava/util/UUID;Lnet/minecraft/network/protocol/game/ClientboundBossEventPacket$Handler;)V");
        }

        public void write(RegistryFriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$AddOperation.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
        }

        protected AddOperation() {
        }
    }

    public interface Handler {

        default void remove(UUID id) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$Handler.remove:(Ljava/util/UUID;)V");
        }
    }

    private interface Operation {

        ClientboundBossEventPacket.OperationType getType();

        void dispatch(UUID id, ClientboundBossEventPacket.Handler handler);

        void write(RegistryFriendlyByteBuf output);
    }

    private enum OperationType {

        ADD,
        REMOVE,
        UPDATE_PROGRESS,
        UPDATE_NAME,
        UPDATE_STYLE,
        UPDATE_PROPERTIES
    }

    private record UpdateNameOperation(Component name) implements ClientboundBossEventPacket.Operation {

        private UpdateNameOperation(RegistryFriendlyByteBuf input) {
            this((Component) null);
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdateNameOperation.<init>:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
        }

        public ClientboundBossEventPacket.OperationType getType() {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdateNameOperation.getType:()Lnet/minecraft/network/protocol/game/ClientboundBossEventPacket$OperationType;");
        }

        public void dispatch(UUID id, ClientboundBossEventPacket.Handler handler) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdateNameOperation.dispatch:(Ljava/util/UUID;Lnet/minecraft/network/protocol/game/ClientboundBossEventPacket$Handler;)V");
        }

        public void write(RegistryFriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdateNameOperation.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
        }
    }

    private record UpdateProgressOperation(float progress) implements ClientboundBossEventPacket.Operation {

        private UpdateProgressOperation(RegistryFriendlyByteBuf input) {
            this((float) 0.0F);
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdateProgressOperation.<init>:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
        }

        public ClientboundBossEventPacket.OperationType getType() {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdateProgressOperation.getType:()Lnet/minecraft/network/protocol/game/ClientboundBossEventPacket$OperationType;");
        }

        public void dispatch(UUID id, ClientboundBossEventPacket.Handler handler) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdateProgressOperation.dispatch:(Ljava/util/UUID;Lnet/minecraft/network/protocol/game/ClientboundBossEventPacket$Handler;)V");
        }

        public void write(RegistryFriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdateProgressOperation.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
        }
    }

    private static class UpdatePropertiesOperation implements ClientboundBossEventPacket.Operation {

        private UpdatePropertiesOperation(boolean darkenScreen, boolean playMusic, boolean createWorldFog) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdatePropertiesOperation.<init>:(ZZZ)V");
        }

        private UpdatePropertiesOperation(RegistryFriendlyByteBuf input) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdatePropertiesOperation.<init>:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
        }

        public ClientboundBossEventPacket.OperationType getType() {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdatePropertiesOperation.getType:()Lnet/minecraft/network/protocol/game/ClientboundBossEventPacket$OperationType;");
        }

        public void dispatch(UUID id, ClientboundBossEventPacket.Handler handler) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdatePropertiesOperation.dispatch:(Ljava/util/UUID;Lnet/minecraft/network/protocol/game/ClientboundBossEventPacket$Handler;)V");
        }

        public void write(RegistryFriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdatePropertiesOperation.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
        }

        protected UpdatePropertiesOperation() {
        }
    }

    private static class UpdateStyleOperation implements ClientboundBossEventPacket.Operation {

        private UpdateStyleOperation(BossEvent.BossBarColor color, BossEvent.BossBarOverlay overlay) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdateStyleOperation.<init>:(Lnet/minecraft/world/BossEvent$BossBarColor;Lnet/minecraft/world/BossEvent$BossBarOverlay;)V");
        }

        private UpdateStyleOperation(RegistryFriendlyByteBuf input) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdateStyleOperation.<init>:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
        }

        public ClientboundBossEventPacket.OperationType getType() {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdateStyleOperation.getType:()Lnet/minecraft/network/protocol/game/ClientboundBossEventPacket$OperationType;");
        }

        public void dispatch(UUID id, ClientboundBossEventPacket.Handler handler) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdateStyleOperation.dispatch:(Ljava/util/UUID;Lnet/minecraft/network/protocol/game/ClientboundBossEventPacket$Handler;)V");
        }

        public void write(RegistryFriendlyByteBuf output) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBossEventPacket$UpdateStyleOperation.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
        }

        protected UpdateStyleOperation() {
        }
    }

    public ClientboundBossEventPacket() {
    }
}
