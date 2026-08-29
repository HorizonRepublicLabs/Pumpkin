package net.minecraft.network.protocol.game;

import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.scores.Objective;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundSetObjectivePacket implements Packet<ClientGamePacketListener> {

    public ClientboundSetObjectivePacket(Objective objective, int method) {
    }

    private ClientboundSetObjectivePacket(RegistryFriendlyByteBuf input) {
    }

    private void write(RegistryFriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetObjectivePacket.write:(Lnet/minecraft/network/RegistryFriendlyByteBuf;)V");
    }

    public PacketType<ClientboundSetObjectivePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetObjectivePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetObjectivePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetObjectivePacket.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public ClientboundSetObjectivePacket() {
    }
}
