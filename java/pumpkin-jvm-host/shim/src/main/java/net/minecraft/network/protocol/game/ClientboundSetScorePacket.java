package net.minecraft.network.protocol.game;

import java.util.Optional;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.numbers.NumberFormat;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundSetScorePacket(String owner, String objectiveName, int score, Optional<Component> display, Optional<NumberFormat> numberFormat) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundSetScorePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetScorePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetScorePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
