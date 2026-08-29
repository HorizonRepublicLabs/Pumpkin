package net.minecraft.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandler;
import io.netty.channel.ChannelOutboundHandler;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.UUID;
import java.util.function.Consumer;
import javax.crypto.Cipher;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketFlow;
import net.minecraft.network.protocol.handshake.ClientIntent;
import net.minecraft.network.protocol.login.ClientLoginPacketListener;
import net.minecraft.network.protocol.status.ClientStatusPacketListener;
import net.minecraft.server.network.EventLoopGroupHolder;
import net.minecraft.util.debugchart.LocalSampleLogger;
import dev.pumpkin.shim.Unimplemented;

public class Connection extends SimpleChannelInboundHandler<Packet<?>> {

    public Connection(PacketFlow receiving) {
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        throw Unimplemented.forMember("net/minecraft/network/Connection.channelActive:(Lio/netty/channel/ChannelHandlerContext;)V");
    }

    public void channelInactive(ChannelHandlerContext ctx) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.channelInactive:(Lio/netty/channel/ChannelHandlerContext;)V");
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.exceptionCaught:(Lio/netty/channel/ChannelHandlerContext;Ljava/lang/Throwable;)V");
    }

    protected void channelRead0(ChannelHandlerContext ctx, Packet<?> packet) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.channelRead0:(Lio/netty/channel/ChannelHandlerContext;Lnet/minecraft/network/protocol/Packet;)V");
    }

    private static <T extends PacketListener> void genericsFtw(Packet<T> packet, PacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.genericsFtw:(Lnet/minecraft/network/protocol/Packet;Lnet/minecraft/network/PacketListener;)V");
    }

    private void validateListener(ProtocolInfo<?> protocol, PacketListener packetListener) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.validateListener:(Lnet/minecraft/network/ProtocolInfo;Lnet/minecraft/network/PacketListener;)V");
    }

    private static void syncAfterConfigurationChange(ChannelFuture future) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.syncAfterConfigurationChange:(Lio/netty/channel/ChannelFuture;)V");
    }

    public <T extends PacketListener> void setupInboundProtocol(ProtocolInfo<T> protocol, T packetListener) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.setupInboundProtocol:(Lnet/minecraft/network/ProtocolInfo;Lnet/minecraft/network/PacketListener;)V");
    }

    public void setupOutboundProtocol(ProtocolInfo<?> protocol) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.setupOutboundProtocol:(Lnet/minecraft/network/ProtocolInfo;)V");
    }

    public void setListenerForServerboundHandshake(PacketListener packetListener) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.setListenerForServerboundHandshake:(Lnet/minecraft/network/PacketListener;)V");
    }

    public void initiateServerboundStatusConnection(String hostName, int port, ClientStatusPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.initiateServerboundStatusConnection:(Ljava/lang/String;ILnet/minecraft/network/protocol/status/ClientStatusPacketListener;)V");
    }

    public void initiateServerboundPlayConnection(String hostName, int port, ClientLoginPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.initiateServerboundPlayConnection:(Ljava/lang/String;ILnet/minecraft/network/protocol/login/ClientLoginPacketListener;)V");
    }

    public <S extends ServerboundPacketListener, C extends ClientboundPacketListener> void initiateServerboundPlayConnection(String hostName, int port, ProtocolInfo<S> outbound, ProtocolInfo<C> inbound, C listener, boolean transfer) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.initiateServerboundPlayConnection:(Ljava/lang/String;ILnet/minecraft/network/ProtocolInfo;Lnet/minecraft/network/ProtocolInfo;Lnet/minecraft/network/ClientboundPacketListener;Z)V");
    }

    private <S extends ServerboundPacketListener, C extends ClientboundPacketListener> void initiateServerboundConnection(String hostName, int port, ProtocolInfo<S> outbound, ProtocolInfo<C> inbound, C listener, ClientIntent intent) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.initiateServerboundConnection:(Ljava/lang/String;ILnet/minecraft/network/ProtocolInfo;Lnet/minecraft/network/ProtocolInfo;Lnet/minecraft/network/ClientboundPacketListener;Lnet/minecraft/network/protocol/handshake/ClientIntent;)V");
    }

    public void send(Packet<?> packet) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.send:(Lnet/minecraft/network/protocol/Packet;)V");
    }

    public void send(Packet<?> packet, ChannelFutureListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.send:(Lnet/minecraft/network/protocol/Packet;Lio/netty/channel/ChannelFutureListener;)V");
    }

    public void send(Packet<?> packet, ChannelFutureListener listener, boolean flush) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.send:(Lnet/minecraft/network/protocol/Packet;Lio/netty/channel/ChannelFutureListener;Z)V");
    }

    public void runOnceConnected(Consumer<Connection> action) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.runOnceConnected:(Ljava/util/function/Consumer;)V");
    }

    private void sendPacket(Packet<?> packet, ChannelFutureListener listener, boolean flush) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.sendPacket:(Lnet/minecraft/network/protocol/Packet;Lio/netty/channel/ChannelFutureListener;Z)V");
    }

    private void doSendPacket(Packet<?> packet, ChannelFutureListener listener, boolean flush) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.doSendPacket:(Lnet/minecraft/network/protocol/Packet;Lio/netty/channel/ChannelFutureListener;Z)V");
    }

    public void flushChannel() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.flushChannel:()V");
    }

    private void flush() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.flush:()V");
    }

    private void flushQueue() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.flushQueue:()V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.tick:()V");
    }

    protected void tickSecond() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.tickSecond:()V");
    }

    public SocketAddress getRemoteAddress() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.getRemoteAddress:()Ljava/net/SocketAddress;");
    }

    public String getLoggableAddress(boolean logIPs) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.getLoggableAddress:(Z)Ljava/lang/String;");
    }

    public void disconnect(Component reason) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.disconnect:(Lnet/minecraft/network/chat/Component;)V");
    }

    public void disconnect(DisconnectionDetails details) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.disconnect:(Lnet/minecraft/network/DisconnectionDetails;)V");
    }

    public boolean isMemoryConnection() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.isMemoryConnection:()Z");
    }

    public PacketFlow getReceiving() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.getReceiving:()Lnet/minecraft/network/protocol/PacketFlow;");
    }

    public PacketFlow getSending() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.getSending:()Lnet/minecraft/network/protocol/PacketFlow;");
    }

    public static Connection connectToServer(InetSocketAddress address, EventLoopGroupHolder eventLoopGroupHolder, LocalSampleLogger bandwidthLogger) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.connectToServer:(Ljava/net/InetSocketAddress;Lnet/minecraft/server/network/EventLoopGroupHolder;Lnet/minecraft/util/debugchart/LocalSampleLogger;)Lnet/minecraft/network/Connection;");
    }

    public static ChannelFuture connect(InetSocketAddress address, EventLoopGroupHolder eventLoopGroupHolder, Connection connection) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.connect:(Ljava/net/InetSocketAddress;Lnet/minecraft/server/network/EventLoopGroupHolder;Lnet/minecraft/network/Connection;)Lio/netty/channel/ChannelFuture;");
    }

    private static String outboundHandlerName(boolean configureOutbound) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.outboundHandlerName:(Z)Ljava/lang/String;");
    }

    private static String inboundHandlerName(boolean configureInbound) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.inboundHandlerName:(Z)Ljava/lang/String;");
    }

    public void configurePacketHandler(ChannelPipeline pipeline) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.configurePacketHandler:(Lio/netty/channel/ChannelPipeline;)V");
    }

    public static void configureSerialization(ChannelPipeline pipeline, PacketFlow inboundDirection, boolean local, BandwidthDebugMonitor monitor) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.configureSerialization:(Lio/netty/channel/ChannelPipeline;Lnet/minecraft/network/protocol/PacketFlow;ZLnet/minecraft/network/BandwidthDebugMonitor;)V");
    }

    private static ChannelOutboundHandler createFrameEncoder(boolean local) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.createFrameEncoder:(Z)Lio/netty/channel/ChannelOutboundHandler;");
    }

    private static ChannelInboundHandler createFrameDecoder(BandwidthDebugMonitor monitor, boolean local) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.createFrameDecoder:(Lnet/minecraft/network/BandwidthDebugMonitor;Z)Lio/netty/channel/ChannelInboundHandler;");
    }

    public static void configureInMemoryPipeline(ChannelPipeline pipeline, PacketFlow packetFlow) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.configureInMemoryPipeline:(Lio/netty/channel/ChannelPipeline;Lnet/minecraft/network/protocol/PacketFlow;)V");
    }

    public static Connection connectToLocalServer(SocketAddress address) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.connectToLocalServer:(Ljava/net/SocketAddress;)Lnet/minecraft/network/Connection;");
    }

    public static Connection fromChannel(Channel channel, PacketFlow flow, LocalSampleLogger bandwidthLogger) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.fromChannel:(Lio/netty/channel/Channel;Lnet/minecraft/network/protocol/PacketFlow;Lnet/minecraft/util/debugchart/LocalSampleLogger;)Lnet/minecraft/network/Connection;");
    }

    public void setEncryptionKey(Cipher decryptCipher, Cipher encryptCipher) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.setEncryptionKey:(Ljavax/crypto/Cipher;Ljavax/crypto/Cipher;)V");
    }

    public boolean isConnected() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.isConnected:()Z");
    }

    public boolean isConnecting() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.isConnecting:()Z");
    }

    public PacketListener getPacketListener() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.getPacketListener:()Lnet/minecraft/network/PacketListener;");
    }

    public DisconnectionDetails getDisconnectionDetails() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.getDisconnectionDetails:()Lnet/minecraft/network/DisconnectionDetails;");
    }

    public void setReadOnly() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.setReadOnly:()V");
    }

    public void setupCompression(int threshold, boolean validateDecompressed) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.setupCompression:(IZ)V");
    }

    public void handleDisconnection() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.handleDisconnection:()V");
    }

    public float getAverageReceivedPackets() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.getAverageReceivedPackets:()F");
    }

    public float getAverageSentPackets() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.getAverageSentPackets:()F");
    }

    public void setBandwidthLogger(LocalSampleLogger bandwidthLogger) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.setBandwidthLogger:(Lnet/minecraft/util/debugchart/LocalSampleLogger;)V");
    }

    public void setIntendedProfileId(UUID profileId) {
        throw Unimplemented.forMember("net/minecraft/network/Connection.setIntendedProfileId:(Ljava/util/UUID;)V");
    }

    public UUID getIntendedProfileId() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.getIntendedProfileId:()Ljava/util/UUID;");
    }

    public Channel channel() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.channel:()Lio/netty/channel/Channel;");
    }

    public PacketFlow getDirection() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.getDirection:()Lnet/minecraft/network/protocol/PacketFlow;");
    }

    public ProtocolInfo<?> getInboundProtocol() {
        throw Unimplemented.forMember("net/minecraft/network/Connection.getInboundProtocol:()Lnet/minecraft/network/ProtocolInfo;");
    }

    public Connection() {
    }
}
