package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundSetPlayerInventoryPacket(int slot, ItemStack contents) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundSetPlayerInventoryPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPlayerInventoryPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetPlayerInventoryPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
