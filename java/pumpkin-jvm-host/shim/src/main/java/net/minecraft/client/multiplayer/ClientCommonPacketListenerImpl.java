package net.minecraft.client.multiplayer;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BooleanSupplier;
import net.minecraft.CrashReport;
import net.minecraft.CrashReportCategory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.dialog.DialogConnectionAccess;
import net.minecraft.core.Holder;
import net.minecraft.nbt.Tag;
import net.minecraft.network.Connection;
import net.minecraft.network.DisconnectionDetails;
import net.minecraft.network.ServerboundPacketListener;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ClientCommonPacketListener;
import net.minecraft.network.protocol.common.ClientboundClearDialogPacket;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.ClientboundCustomReportDetailsPacket;
import net.minecraft.network.protocol.common.ClientboundDisconnectPacket;
import net.minecraft.network.protocol.common.ClientboundKeepAlivePacket;
import net.minecraft.network.protocol.common.ClientboundPingPacket;
import net.minecraft.network.protocol.common.ClientboundResourcePackPopPacket;
import net.minecraft.network.protocol.common.ClientboundResourcePackPushPacket;
import net.minecraft.network.protocol.common.ClientboundServerLinksPacket;
import net.minecraft.network.protocol.common.ClientboundShowDialogPacket;
import net.minecraft.network.protocol.common.ClientboundStoreCookiePacket;
import net.minecraft.network.protocol.common.ClientboundTransferPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.network.protocol.cookie.ClientboundCookieRequestPacket;
import net.minecraft.resources.Identifier;
import net.minecraft.server.ServerLinks;
import net.minecraft.server.dialog.Dialog;
import dev.pumpkin.shim.Unimplemented;

public abstract class ClientCommonPacketListenerImpl implements ClientCommonPacketListener {

    protected ClientCommonPacketListenerImpl(Minecraft minecraft, Connection connection, CommonListenerCookie cookie) {
    }

    public ServerLinks serverLinks() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.serverLinks:()Lnet/minecraft/server/ServerLinks;");
    }

    public void onPacketError(Packet packet, Exception cause) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.onPacketError:(Lnet/minecraft/network/protocol/Packet;Ljava/lang/Exception;)V");
    }

    public DisconnectionDetails createDisconnectionInfo(Component reason, Throwable cause) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.createDisconnectionInfo:(Lnet/minecraft/network/chat/Component;Ljava/lang/Throwable;)Lnet/minecraft/network/DisconnectionDetails;");
    }

    public boolean shouldHandleMessage(Packet<?> packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.shouldHandleMessage:(Lnet/minecraft/network/protocol/Packet;)Z");
    }

    public void handleKeepAlive(ClientboundKeepAlivePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.handleKeepAlive:(Lnet/minecraft/network/protocol/common/ClientboundKeepAlivePacket;)V");
    }

    public void handlePing(ClientboundPingPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.handlePing:(Lnet/minecraft/network/protocol/common/ClientboundPingPacket;)V");
    }

    public void handleCustomPayload(ClientboundCustomPayloadPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.handleCustomPayload:(Lnet/minecraft/network/protocol/common/ClientboundCustomPayloadPacket;)V");
    }

    protected abstract void handleCustomPayload(CustomPacketPayload payload);

    public void handleResourcePackPush(ClientboundResourcePackPushPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.handleResourcePackPush:(Lnet/minecraft/network/protocol/common/ClientboundResourcePackPushPacket;)V");
    }

    public void handleResourcePackPop(ClientboundResourcePackPopPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.handleResourcePackPop:(Lnet/minecraft/network/protocol/common/ClientboundResourcePackPopPacket;)V");
    }

    public void handleRequestCookie(ClientboundCookieRequestPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.handleRequestCookie:(Lnet/minecraft/network/protocol/cookie/ClientboundCookieRequestPacket;)V");
    }

    public void handleStoreCookie(ClientboundStoreCookiePacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.handleStoreCookie:(Lnet/minecraft/network/protocol/common/ClientboundStoreCookiePacket;)V");
    }

    public void handleCustomReportDetails(ClientboundCustomReportDetailsPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.handleCustomReportDetails:(Lnet/minecraft/network/protocol/common/ClientboundCustomReportDetailsPacket;)V");
    }

    public void handleServerLinks(ClientboundServerLinksPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.handleServerLinks:(Lnet/minecraft/network/protocol/common/ClientboundServerLinksPacket;)V");
    }

    public void handleShowDialog(ClientboundShowDialogPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.handleShowDialog:(Lnet/minecraft/network/protocol/common/ClientboundShowDialogPacket;)V");
    }

    protected abstract DialogConnectionAccess createDialogAccess();

    public void handleClearDialog(ClientboundClearDialogPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.handleClearDialog:(Lnet/minecraft/network/protocol/common/ClientboundClearDialogPacket;)V");
    }

    public void handleTransfer(ClientboundTransferPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.handleTransfer:(Lnet/minecraft/network/protocol/common/ClientboundTransferPacket;)V");
    }

    public void handleDisconnect(ClientboundDisconnectPacket packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.handleDisconnect:(Lnet/minecraft/network/protocol/common/ClientboundDisconnectPacket;)V");
    }

    public void send(Packet<?> packet) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.send:(Lnet/minecraft/network/protocol/Packet;)V");
    }

    public void onDisconnect(DisconnectionDetails details) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.onDisconnect:(Lnet/minecraft/network/DisconnectionDetails;)V");
    }

    public void fillListenerSpecificCrashDetails(CrashReport report, CrashReportCategory connectionDetails) {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.fillListenerSpecificCrashDetails:(Lnet/minecraft/CrashReport;Lnet/minecraft/CrashReportCategory;)V");
    }

    protected abstract class CommonDialogAccess implements DialogConnectionAccess {

        public void disconnect(Component message) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl$CommonDialogAccess.disconnect:(Lnet/minecraft/network/chat/Component;)V");
        }

        public void openDialog(Holder<Dialog> dialog, Screen activeScreen) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl$CommonDialogAccess.openDialog:(Lnet/minecraft/core/Holder;Lnet/minecraft/client/gui/screens/Screen;)V");
        }

        public void sendCustomAction(Identifier id, Optional<Tag> payload) {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl$CommonDialogAccess.sendCustomAction:(Lnet/minecraft/resources/Identifier;Ljava/util/Optional;)V");
        }

        public ServerLinks serverLinks() {
            throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl$CommonDialogAccess.serverLinks:()Lnet/minecraft/server/ServerLinks;");
        }

        protected CommonDialogAccess() {
        }
    }

    private record DeferredPacket(Packet<? extends ServerboundPacketListener> packet, BooleanSupplier sendCondition, long expirationTime) {
    }

    private class PackConfirmScreen extends ConfirmScreen {

        private PackConfirmScreen(Minecraft minecraft, Screen parentScreen, List<ClientCommonPacketListenerImpl.PackConfirmScreen.PendingRequest> requests, boolean required, Component prompt) {
        }

        private record PendingRequest(UUID id, URL url, String hash) {
        }

        protected PackConfirmScreen() {
        }
    }

    public Connection getConnection() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.getConnection:()Lnet/minecraft/network/Connection;");
    }

    public net.minecraft.network.PacketProcessor getPacketProcessor() {
        throw Unimplemented.forMember("net/minecraft/client/multiplayer/ClientCommonPacketListenerImpl.getPacketProcessor:()Lnet/minecraft/network/PacketProcessor;");
    }

    public ClientCommonPacketListenerImpl() {
    }
}
