package net.minecraft.network.protocol.game;

import java.util.List;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.network.syncher.SynchedEntityData;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundSetEntityDataPacket(int id, List<SynchedEntityData.DataValue<?>> packedItems) implements Packet<ClientGamePacketListener> {

    private ClientboundSetEntityDataPacket(RegistryFriendlyByteBuf input) {
        this((int) 0, (List<SynchedEntityData.DataValue<?>>) null);
    }

    private static List<SynchedEntityData.DataValue<?>> unpack(RegistryFriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEntityDataPacket.unpack:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)Ljava/util/List;");
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEntityDataPacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetEntityDataPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEntityDataPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetEntityDataPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
