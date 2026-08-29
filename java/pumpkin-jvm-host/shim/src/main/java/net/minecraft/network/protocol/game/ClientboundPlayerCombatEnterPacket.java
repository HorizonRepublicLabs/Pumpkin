package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundPlayerCombatEnterPacket implements Packet<ClientGamePacketListener> {

    protected ClientboundPlayerCombatEnterPacket() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerCombatEnterPacket.<init>:()V");
    }

    public PacketType<ClientboundPlayerCombatEnterPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerCombatEnterPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerCombatEnterPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
