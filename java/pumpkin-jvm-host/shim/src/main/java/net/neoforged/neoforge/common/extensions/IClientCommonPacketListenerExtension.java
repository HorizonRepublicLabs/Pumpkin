package net.neoforged.neoforge.common.extensions;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import dev.pumpkin.shim.Unimplemented;

public interface IClientCommonPacketListenerExtension extends ICommonPacketListener {

    default void send(CustomPacketPayload payload) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IClientCommonPacketListenerExtension.send:(Lnet/minecraft/network/protocol/common/custom/CustomPacketPayload;)V");
    }

    default void disconnect(Component reason) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IClientCommonPacketListenerExtension.disconnect:(Lnet/minecraft/network/chat/Component;)V");
    }
}
