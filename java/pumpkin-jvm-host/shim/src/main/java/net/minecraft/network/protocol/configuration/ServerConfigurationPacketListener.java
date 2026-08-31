package net.minecraft.network.protocol.configuration;

import net.minecraft.network.ConnectionProtocol;
import net.minecraft.network.protocol.common.ServerCommonPacketListener;
import net.neoforged.neoforge.common.extensions.IServerConfigurationPacketListenerExtension;
import dev.pumpkin.shim.Unimplemented;

public interface ServerConfigurationPacketListener extends ServerCommonPacketListener, IServerConfigurationPacketListenerExtension {

    default ConnectionProtocol protocol() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/configuration/ServerConfigurationPacketListener.protocol:()Lnet/minecraft/network/ConnectionProtocol;");
    }

    void handleConfigurationFinished(ServerboundFinishConfigurationPacket packet);

    void handleSelectKnownPacks(ServerboundSelectKnownPacks packet);

    void handleAcceptCodeOfConduct(ServerboundAcceptCodeOfConductPacket packet);
}
