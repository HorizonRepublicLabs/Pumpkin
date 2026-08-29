package net.minecraft.network.protocol.game;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.stats.Stat;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundAwardStatsPacket(Object2IntMap<Stat<?>> stats) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundAwardStatsPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAwardStatsPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundAwardStatsPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
