package net.minecraft.network.protocol.game;

import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundPlayerCombatKillPacket(int playerId, Component message) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundPlayerCombatKillPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerCombatKillPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerCombatKillPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public boolean isSkippable() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerCombatKillPacket.isSkippable:()Z");
    }
}
