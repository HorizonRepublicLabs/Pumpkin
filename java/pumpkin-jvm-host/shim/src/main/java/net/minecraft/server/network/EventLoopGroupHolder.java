package net.minecraft.server.network;

import io.netty.channel.Channel;
import io.netty.channel.IoHandlerFactory;
import io.netty.channel.ServerChannel;

public abstract class EventLoopGroupHolder {

    private EventLoopGroupHolder(String type, Class<? extends Channel> channelCls, Class<? extends ServerChannel> serverChannelCls) {
    }

    protected abstract IoHandlerFactory ioHandlerFactory();

    public EventLoopGroupHolder() {
    }
}
