package net.minecraft.network.protocol;

import net.neoforged.neoforge.common.extensions.IPacketFlowExtension;
import dev.pumpkin.shim.Unimplemented;

public enum PacketFlow implements IPacketFlowExtension {

    SERVERBOUND, CLIENTBOUND;

    public String id() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/PacketFlow.id:()Ljava/lang/String;");
    }
}
