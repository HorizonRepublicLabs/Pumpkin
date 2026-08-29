package net.minecraft.network;

import net.minecraft.network.protocol.PacketFlow;
import dev.pumpkin.shim.Unimplemented;

public interface ServerboundPacketListener extends PacketListener {

    default PacketFlow flow() {
        throw Unimplemented.forMember("net/minecraft/network/ServerboundPacketListener.flow:()Lnet/minecraft/network/protocol/PacketFlow;");
    }
}
