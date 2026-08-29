package net.minecraft.network.protocol.game;

import java.util.List;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundContainerSetContentPacket(int containerId, int stateId, List<ItemStack> items, ItemStack carriedItem) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundContainerSetContentPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundContainerSetContentPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundContainerSetContentPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
