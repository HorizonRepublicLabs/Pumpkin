package net.neoforged.neoforge.client.event;

import net.minecraft.client.multiplayer.MultiPlayerGameMode;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.Connection;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public abstract class ClientPlayerNetworkEvent extends Event {

    protected ClientPlayerNetworkEvent(final MultiPlayerGameMode multiPlayerGameMode, final LocalPlayer player, final Connection connection) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ClientPlayerNetworkEvent.<init>:(Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;Lnet/minecraft/client/player/LocalPlayer;Lnet/minecraft/network/Connection;)V");
    }

    public LocalPlayer getPlayer() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ClientPlayerNetworkEvent.getPlayer:()Lnet/minecraft/client/player/LocalPlayer;");
    }

    public Connection getConnection() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ClientPlayerNetworkEvent.getConnection:()Lnet/minecraft/network/Connection;");
    }

    public static class LoggingIn extends ClientPlayerNetworkEvent {

        public LoggingIn(final MultiPlayerGameMode controller, final LocalPlayer player, final Connection networkManager) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ClientPlayerNetworkEvent$LoggingIn.<init>:(Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;Lnet/minecraft/client/player/LocalPlayer;Lnet/minecraft/network/Connection;)V");
        }

        public LoggingIn() {
        }
    }

    public static class LoggingOut extends ClientPlayerNetworkEvent {

        public LoggingOut(final MultiPlayerGameMode controller, final LocalPlayer player, final Connection networkManager) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ClientPlayerNetworkEvent$LoggingOut.<init>:(Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;Lnet/minecraft/client/player/LocalPlayer;Lnet/minecraft/network/Connection;)V");
        }

        public MultiPlayerGameMode getMultiPlayerGameMode() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ClientPlayerNetworkEvent$LoggingOut.getMultiPlayerGameMode:()Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;");
        }

        public LocalPlayer getPlayer() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ClientPlayerNetworkEvent$LoggingOut.getPlayer:()Lnet/minecraft/client/player/LocalPlayer;");
        }

        public Connection getConnection() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ClientPlayerNetworkEvent$LoggingOut.getConnection:()Lnet/minecraft/network/Connection;");
        }

        public LoggingOut() {
        }
    }

    public static class Clone extends ClientPlayerNetworkEvent {

        public Clone(final MultiPlayerGameMode pc, final LocalPlayer oldPlayer, final LocalPlayer newPlayer, final Connection networkManager) {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ClientPlayerNetworkEvent$Clone.<init>:(Lnet/minecraft/client/multiplayer/MultiPlayerGameMode;Lnet/minecraft/client/player/LocalPlayer;Lnet/minecraft/client/player/LocalPlayer;Lnet/minecraft/network/Connection;)V");
        }

        public LocalPlayer getPlayer() {
            throw Unimplemented.forMember("net/neoforged/neoforge/client/event/ClientPlayerNetworkEvent$Clone.getPlayer:()Lnet/minecraft/client/player/LocalPlayer;");
        }

        public Clone() {
        }
    }

    public ClientPlayerNetworkEvent() {
    }
}
