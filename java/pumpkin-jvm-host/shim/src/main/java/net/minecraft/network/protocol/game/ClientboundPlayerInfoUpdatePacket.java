package net.minecraft.network.protocol.game;

import com.mojang.authlib.GameProfile;
import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.UUID;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.RemoteChatSession;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.GameType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundPlayerInfoUpdatePacket implements Packet<ClientGamePacketListener> {

    public ClientboundPlayerInfoUpdatePacket(EnumSet<ClientboundPlayerInfoUpdatePacket.Action> actions, Collection<ServerPlayer> players) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoUpdatePacket.<init>:(Ljava/util/EnumSet;Ljava/util/Collection;)V");
    }

    public ClientboundPlayerInfoUpdatePacket(ClientboundPlayerInfoUpdatePacket.Action action, ServerPlayer player) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoUpdatePacket.<init>:(Lnet/minecraft/network/protocol/game/ClientboundPlayerInfoUpdatePacket$Action;Lnet/minecraft/server/level/ServerPlayer;)V");
    }

    private ClientboundPlayerInfoUpdatePacket(RegistryFriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoUpdatePacket.<init>:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoUpdatePacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundPlayerInfoUpdatePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoUpdatePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoUpdatePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public List<ClientboundPlayerInfoUpdatePacket.Entry> entries() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoUpdatePacket.entries:()Ljava/util/List;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoUpdatePacket.toString:()Ljava/lang/String;");
    }

    public enum Action {

        ADD_PLAYER,
        INITIALIZE_CHAT,
        UPDATE_GAME_MODE,
        UPDATE_LISTED,
        UPDATE_LATENCY,
        UPDATE_DISPLAY_NAME,
        UPDATE_LIST_ORDER,
        UPDATE_HAT;

        public interface Reader {

            void read(ClientboundPlayerInfoUpdatePacket.EntryBuilder entry, RegistryFriendlyByteBuf input);
        }

        public interface Writer {

            void write(RegistryFriendlyByteBuf output, ClientboundPlayerInfoUpdatePacket.Entry entry);
        }
    }

    public record Entry(UUID profileId, GameProfile profile, boolean listed, int latency, GameType gameMode, Component displayName, boolean showHat, int listOrder, RemoteChatSession.Data chatSession) {

        private Entry(ServerPlayer player) {
            this((UUID) null, (GameProfile) null, (boolean) false, (int) 0, (GameType) null, (Component) null, (boolean) false, (int) 0, (RemoteChatSession.Data) null);
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoUpdatePacket$Entry.<init>:(Lnet/minecraft/server/level/ServerPlayer;)V");
        }
    }

    private static class EntryBuilder {

        private EntryBuilder(UUID profileId) {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoUpdatePacket$EntryBuilder.<init>:(Ljava/util/UUID;)V");
        }

        private ClientboundPlayerInfoUpdatePacket.Entry build() {
            throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoUpdatePacket$EntryBuilder.build:()Lnet/minecraft/network/protocol/game/ClientboundPlayerInfoUpdatePacket$Entry;");
        }

        protected EntryBuilder() {
        }
    }

    public ClientboundPlayerInfoUpdatePacket() {
    }
}
