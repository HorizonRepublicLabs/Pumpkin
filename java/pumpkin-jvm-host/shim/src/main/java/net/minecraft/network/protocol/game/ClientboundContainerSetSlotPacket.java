package net.minecraft.network.protocol.game;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundContainerSetSlotPacket implements Packet<ClientGamePacketListener> {

    public ClientboundContainerSetSlotPacket(int containerId, int stateId, int slot, ItemStack itemStack) {
    }

    private ClientboundContainerSetSlotPacket(RegistryFriendlyByteBuf input) {
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundContainerSetSlotPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundContainerSetSlotPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundContainerSetSlotPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundContainerSetSlotPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public ItemStack getItem() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundContainerSetSlotPacket.getItem:()Lnet/minecraft/world/item/ItemStack;");
    }

    public ClientboundContainerSetSlotPacket() {
    }
}
