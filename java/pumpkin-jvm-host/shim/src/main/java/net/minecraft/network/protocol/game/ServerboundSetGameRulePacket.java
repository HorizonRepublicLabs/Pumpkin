package net.minecraft.network.protocol.game;

import java.util.List;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.gamerules.GameRule;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundSetGameRulePacket(List<ServerboundSetGameRulePacket.Entry> entries) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundSetGameRulePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetGameRulePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundSetGameRulePacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }

    public record Entry(ResourceKey<GameRule<?>> gameRuleKey, String value) {
    }
}
