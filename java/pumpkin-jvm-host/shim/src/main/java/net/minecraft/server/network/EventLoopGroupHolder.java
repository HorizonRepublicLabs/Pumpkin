package net.minecraft.server.network;

import io.netty.channel.Channel;
import io.netty.channel.IoHandlerFactory;
import io.netty.channel.ServerChannel;
import dev.pumpkin.shim.Unimplemented;

public abstract class EventLoopGroupHolder {

    private EventLoopGroupHolder(String type, Class<? extends Channel> channelCls, Class<? extends ServerChannel> serverChannelCls) {
        throw Unimplemented.forMember("net/minecraft/server/network/EventLoopGroupHolder.<init>:(Ljava/lang/String;Ljava/lang/Class;Ljava/lang/Class;)V");
    }

    protected abstract IoHandlerFactory ioHandlerFactory();

    public EventLoopGroupHolder() {
    }
}
