package net.minecraft.network;

import net.minecraft.network.protocol.PacketFlow;

public interface PacketListener {

    PacketFlow flow();

    ConnectionProtocol protocol();

    void onDisconnect(DisconnectionDetails details);

    boolean isAcceptingMessages();
}
