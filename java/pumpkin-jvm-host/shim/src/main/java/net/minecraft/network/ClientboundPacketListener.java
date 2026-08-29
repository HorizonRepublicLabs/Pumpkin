package net.minecraft.network;

import net.minecraft.network.protocol.PacketFlow;
import dev.pumpkin.shim.Unimplemented;

public interface ClientboundPacketListener extends PacketListener {

    default PacketFlow flow() {
        throw Unimplemented.forMember("net/minecraft/network/ClientboundPacketListener.flow:()Lnet/minecraft/network/protocol/PacketFlow;");
    }
}
