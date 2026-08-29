package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundTickingStatePacket(float tickRate, boolean isFrozen) implements Packet<ClientGamePacketListener> {

    private ClientboundTickingStatePacket(FriendlyByteBuf input) {
        this((float) 0.0F, (boolean) false);
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTickingStatePacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundTickingStatePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTickingStatePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundTickingStatePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
