package net.minecraft.server.network;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.Timeout;
import net.minecraft.server.MinecraftServer;
import dev.pumpkin.shim.Unimplemented;

public class ServerConnectionListener {

    public ServerConnectionListener(MinecraftServer server) {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerConnectionListener.<init>:(Lnet/minecraft/server/MinecraftServer;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerConnectionListener.tick:()V");
    }

    public MinecraftServer getServer() {
        throw Unimplemented.forMember("net/minecraft/server/network/ServerConnectionListener.getServer:()Lnet/minecraft/server/MinecraftServer;");
    }

    private static class LatencySimulator extends ChannelInboundHandlerAdapter {

        public LatencySimulator(int delay, int jitter) {
            throw Unimplemented.forMember("net/minecraft/server/network/ServerConnectionListener$LatencySimulator.<init>:(II)V");
        }

        public void channelRead(ChannelHandlerContext ctx, Object msg) {
            throw Unimplemented.forMember("net/minecraft/server/network/ServerConnectionListener$LatencySimulator.channelRead:(Lio/netty/channel/ChannelHandlerContext;Ljava/lang/Object;)V");
        }

        private void delayDownstream(ChannelHandlerContext ctx, Object msg) {
            throw Unimplemented.forMember("net/minecraft/server/network/ServerConnectionListener$LatencySimulator.delayDownstream:(Lio/netty/channel/ChannelHandlerContext;Ljava/lang/Object;)V");
        }

        private void onTimeout(Timeout timeout) {
            throw Unimplemented.forMember("net/minecraft/server/network/ServerConnectionListener$LatencySimulator.onTimeout:(Lio/netty/util/Timeout;)V");
        }

        private record DelayedMessage(ChannelHandlerContext ctx, Object msg) {
        }

        protected LatencySimulator() {
        }
    }

    public ServerConnectionListener() {
    }
}
