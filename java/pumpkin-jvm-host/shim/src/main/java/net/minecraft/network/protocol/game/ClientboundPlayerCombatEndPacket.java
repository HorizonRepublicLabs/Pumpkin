package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.damagesource.CombatTracker;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundPlayerCombatEndPacket implements Packet<ClientGamePacketListener> {

    public ClientboundPlayerCombatEndPacket(CombatTracker tracker) {
    }

    public ClientboundPlayerCombatEndPacket(int duration) {
    }

    private ClientboundPlayerCombatEndPacket(FriendlyByteBuf input) {
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerCombatEndPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundPlayerCombatEndPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerCombatEndPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerCombatEndPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    public ClientboundPlayerCombatEndPacket() {
    }
}
