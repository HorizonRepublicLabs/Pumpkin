package net.neoforged.neoforge.common.extensions;

import net.minecraft.network.Connection;
import net.minecraft.network.PacketListener;
import net.minecraft.network.PacketProcessor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.connection.ConnectionType;

public interface ICommonPacketListener extends PacketListener {

    void send(Packet<?> packet);

    void send(CustomPacketPayload payload);

    void disconnect(Component reason);

    Connection getConnection();

    PacketProcessor getPacketProcessor();

    ConnectionType getConnectionType();
}
