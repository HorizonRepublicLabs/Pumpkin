package net.minecraft.network.protocol.login;

import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.protocol.cookie.ClientCookiePacketListener;
import dev.pumpkin.shim.Unimplemented;

public interface ClientLoginPacketListener extends ClientCookiePacketListener {

    default ConnectionProtocol protocol() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/login/ClientLoginPacketListener.protocol:()Lnet/minecraft/network/ConnectionProtocol;");
    }

    void handleHello(ClientboundHelloPacket packet);

    void handleLoginFinished(ClientboundLoginFinishedPacket packet);

    void handleDisconnect(ClientboundLoginDisconnectPacket packet);

    void handleCompression(ClientboundLoginCompressionPacket packet);

    void handleCustomQuery(ClientboundCustomQueryPacket packet);
}
