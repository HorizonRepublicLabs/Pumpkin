package net.minecraft.network.protocol;

import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record PacketType<T extends Packet<?>>(PacketFlow flow, Identifier id) {

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/PacketType.toString:()Ljava/lang/String;");
    }
}
