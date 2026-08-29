package net.minecraft.network.protocol.game;

import java.util.Map;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.gamerules.GameRule;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundGameRuleValuesPacket(Map<ResourceKey<GameRule<?>>, String> values) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundGameRuleValuesPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundGameRuleValuesPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundGameRuleValuesPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
