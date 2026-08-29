package net.minecraft.network.protocol.game;

import java.util.List;
import java.util.UUID;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundPlayerInfoRemovePacket(List<UUID> profileIds) implements Packet<ClientGamePacketListener> {

    private ClientboundPlayerInfoRemovePacket(FriendlyByteBuf input) {
        this((List<UUID>) null);
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoRemovePacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoRemovePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundPlayerInfoRemovePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoRemovePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerInfoRemovePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
