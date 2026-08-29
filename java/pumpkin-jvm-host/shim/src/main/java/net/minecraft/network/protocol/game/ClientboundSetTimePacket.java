package net.minecraft.network.protocol.game;

import java.util.Map;
import net.minecraft.core.Holder;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.clock.ClockNetworkState;
import net.minecraft.world.clock.WorldClock;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundSetTimePacket(long gameTime, Map<Holder<WorldClock>, ClockNetworkState> clockUpdates) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundSetTimePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetTimePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundSetTimePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
