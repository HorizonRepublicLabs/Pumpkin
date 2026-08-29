package net.neoforged.neoforge.common.extensions;

import io.netty.channel.ChannelFutureListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import dev.pumpkin.shim.Unimplemented;

public interface IServerCommonPacketListenerExtension extends ICommonPacketListener {

    default void send(CustomPacketPayload payload) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IServerCommonPacketListenerExtension.send:(Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;)V");
    }

    void send(Packet<?> packet, ChannelFutureListener listener);

    default void send(CustomPacketPayload payload, ChannelFutureListener listener) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IServerCommonPacketListenerExtension.send:(Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;Lio/netty/channel/ChannelFutureListener;)V");
    }
}
