package net.minecraft.network.protocol.game;

import com.mojang.datafixers.util.Pair;
import java.util.List;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetEquipmentPacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetEquipmentPacket(int entity, List<Pair<EquipmentSlot, ItemStack>> slots) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEquipmentPacket.<init>:(ILjava/util/List;)V");
    }

    private ClientboundSetEquipmentPacket(RegistryFriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEquipmentPacket.<init>:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEquipmentPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetEquipmentPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEquipmentPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEquipmentPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    protected ClientboundSetEquipmentPacket() {
    }
}
