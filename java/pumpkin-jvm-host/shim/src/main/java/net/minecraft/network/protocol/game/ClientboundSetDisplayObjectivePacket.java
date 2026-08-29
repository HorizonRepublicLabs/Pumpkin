package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.scores.DisplaySlot;
import net.minecraft.world.scores.Objective;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetDisplayObjectivePacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetDisplayObjectivePacket(DisplaySlot slot, Objective objective) {
    }

    private ClientboundSetDisplayObjectivePacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetDisplayObjectivePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetDisplayObjectivePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetDisplayObjectivePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetDisplayObjectivePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public ClientboundSetDisplayObjectivePacket() {
    }
}
