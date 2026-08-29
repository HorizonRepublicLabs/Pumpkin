package net.minecraft.network.protocol.status;

import net.minecraft.network.ClientboundPacketListener;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.protocol.ping.ClientPongPacketListener;
import dev.pumpkin.shim.Unimplemented;

public interface ClientStatusPacketListener extends ClientboundPacketListener, ClientPongPacketListener {

    default ConnectionProtocol protocol() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/status/ClientStatusPacketListener.protocol:()Lnet/minecraft/network/ConnectionProtocol;");
    }

    void handleStatusResponse(ClientboundStatusResponsePacket packet);
}
