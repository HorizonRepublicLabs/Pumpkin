package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSelectAdvancementsTabPacket implements Packet<ClientGamePacketListener> {

    public ClientboundSelectAdvancementsTabPacket(Identifier tab) {
    }

    private ClientboundSelectAdvancementsTabPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSelectAdvancementsTabPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSelectAdvancementsTabPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSelectAdvancementsTabPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSelectAdvancementsTabPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public ClientboundSelectAdvancementsTabPacket() {
    }
}
