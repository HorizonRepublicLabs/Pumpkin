package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundSetCreativeModeSlotPacket(short slotNum, ItemStack itemStack) implements Packet<ServerGamePacketListener> {

    public ServerboundSetCreativeModeSlotPacket(int slotNum, ItemStack itemStack) {
        this((short) 0, (ItemStack) null);
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetCreativeModeSlotPacket.<init>:(ILnet/minecraft/world/item/ItemStack;)V");
    }

    public PacketType<ServerboundSetCreativeModeSlotPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetCreativeModeSlotPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetCreativeModeSlotPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
