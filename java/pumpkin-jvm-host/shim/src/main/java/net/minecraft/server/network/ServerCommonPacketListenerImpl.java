package net.minecraft.server.network;

import com.mojang.authlib.GameProfile;
import io.netty.channel.ChannelFutureListener;
import net.minecraft.ReportedException;
import net.minecraft.network.Connection;
import net.minecraft.network.DisconnectionDetails;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ServerCommonPacketListener;
import net.minecraft.network.protocol.common.ServerboundCustomClickActionPacket;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.ServerboundKeepAlivePacket;
import net.minecraft.network.protocol.common.ServerboundPongPacket;
import net.minecraft.network.protocol.common.ServerboundResourcePackPacket;
import net.minecraft.network.protocol.cookie.ServerboundCookieResponsePacket;
import net.minecraft.server.MinecraftServer;
import dev.pumpkin.shim.Unimplemented;

public abstract class ServerCommonPacketListenerImpl implements ServerCommonPacketListener {

    public ServerCommonPacketListenerImpl(MinecraftServer server, Connection connection, CommonListenerCookie cookie) {
    }

    private void close() {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.close:()V");
    }

    public void onDisconnect(DisconnectionDetails details) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.onDisconnect:(Lnet/minecraft/network/DisconnectionDetails;)V");
    }

    public void onPacketError(Packet packet, Exception e) throws ReportedException {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.onPacketError:(Lnet/minecraft/network/protocol/Packet;Ljava/lang/Exception;)V");
    }

    public void handleKeepAlive(ServerboundKeepAlivePacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.handleKeepAlive:(Lnet/minecraft/network/protocol/common/ServerboundKeepAlivePacket;)V");
    }

    public void handlePong(ServerboundPongPacket serverboundPongPacket) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.handlePong:(Lnet/minecraft/network/protocol/common/ServerboundPongPacket;)V");
    }

    public void handleCustomPayload(ServerboundCustomPayloadPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.handleCustomPayload:(Lnet/minecraft/network/protocol/common/ServerboundCustomPayloadPacket;)V");
    }

    public void handleCustomClickAction(ServerboundCustomClickActionPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.handleCustomClickAction:(Lnet/minecraft/network/protocol/common/ServerboundCustomClickActionPacket;)V");
    }

    public void handleResourcePackResponse(ServerboundResourcePackPacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.handleResourcePackResponse:(Lnet/minecraft/network/protocol/common/ServerboundResourcePackPacket;)V");
    }

    public void handleCookieResponse(ServerboundCookieResponsePacket packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.handleCookieResponse:(Lnet/minecraft/network/protocol/cookie/ServerboundCookieResponsePacket;)V");
    }

    public void send(Packet<?> packet) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.send:(Lnet/minecraft/network/protocol/Packet;)V");
    }

    public void send(Packet<?> packet, ChannelFutureListener listener) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.send:(Lnet/minecraft/network/protocol/Packet;Lio/netty/channel/ChannelFutureListener;)V");
    }

    public void disconnect(Component reason) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.disconnect:(Lnet/minecraft/network/chat/Component;)V");
    }

    public void disconnect(DisconnectionDetails details) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.disconnect:(Lnet/minecraft/network/DisconnectionDetails;)V");
    }

    protected abstract GameProfile playerProfile();

    public GameProfile getOwner() {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.getOwner:()Lcom/mojang/authlib/GameProfile;");
    }

    public Connection getConnection() {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.getConnection:()Lnet/minecraft/network/Connection;");
    }

    public net.minecraft.network.PacketProcessor getPacketProcessor() {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.getPacketProcessor:()Lnet/minecraft/network/PacketProcessor;");
    }

    public net.neoforged.neoforge.network.connection.ConnectionType getConnectionType() {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerCommonPacketListenerImpl.getConnectionType:()Lnet/neoforged/neoforge/network/connection/ConnectionType;");
    }

    public ServerCommonPacketListenerImpl() {
    }
}
