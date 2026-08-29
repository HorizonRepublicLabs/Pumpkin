package net.minecraft.network.protocol.game;

import java.util.Set;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.util.debug.DebugSubscription;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundDebugSubscriptionRequestPacket(Set<DebugSubscription<?>> subscriptions) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundDebugSubscriptionRequestPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundDebugSubscriptionRequestPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundDebugSubscriptionRequestPacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
