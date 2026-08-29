package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundTickingStepPacket(int tickSteps) implements Packet<ClientGamePacketListener> {

    private ClientboundTickingStepPacket(FriendlyByteBuf input) {
        this((int) 0);
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTickingStepPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTickingStepPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundTickingStepPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTickingStepPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTickingStepPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
