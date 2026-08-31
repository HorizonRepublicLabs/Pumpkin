package net.minecraft.network.protocol.configuration;

import java.util.List;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.server.packs.repository.KnownPack;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundSelectKnownPacks(List<KnownPack> knownPacks) implements Packet<ServerConfigurationPacketListener> {

    public PacketType<ServerboundSelectKnownPacks> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/configuration/ServerboundSelectKnownPacks.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerConfigurationPacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/configuration/ServerboundSelectKnownPacks.handle:(Lnet/minecraft/network/protocol/configuration/ServerConfigurationPacketListener;)V");
    }
}
