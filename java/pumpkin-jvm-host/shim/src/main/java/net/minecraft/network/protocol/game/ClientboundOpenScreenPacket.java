package net.minecraft.network.protocol.game;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.inventory.MenuType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundOpenScreenPacket implements Packet<ClientGamePacketListener> {

    public ClientboundOpenScreenPacket(int containerId, MenuType<?> type, Component title) {
    }

    public PacketType<ClientboundOpenScreenPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundOpenScreenPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundOpenScreenPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public MenuType<?> getType() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundOpenScreenPacket.getType:()Lnet/minecraft/world/inventory/MenuType;");
    }

    public ClientboundOpenScreenPacket() {
    }
}
