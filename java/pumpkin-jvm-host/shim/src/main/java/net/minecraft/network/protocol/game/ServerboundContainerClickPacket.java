package net.minecraft.network.protocol.game;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.network.HashedStack;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.inventory.ContainerInput;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundContainerClickPacket(int containerId, int stateId, short slotNum, byte buttonNum, ContainerInput containerInput, Int2ObjectMap<HashedStack> changedSlots, HashedStack carriedItem) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundContainerClickPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundContainerClickPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundContainerClickPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
