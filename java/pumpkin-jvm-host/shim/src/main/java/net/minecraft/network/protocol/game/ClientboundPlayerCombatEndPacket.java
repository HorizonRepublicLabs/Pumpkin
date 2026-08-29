package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.damagesource.CombatTracker;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundPlayerCombatEndPacket implements Packet<ClientGamePacketListener> {

    public ClientboundPlayerCombatEndPacket(CombatTracker tracker) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerCombatEndPacket.<init>:(Lnet/minecraft/world/damagesource/CombatTracker;)V");
    }

    public ClientboundPlayerCombatEndPacket(int duration) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerCombatEndPacket.<init>:(I)V");
    }

    private ClientboundPlayerCombatEndPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerCombatEndPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
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

    protected ClientboundPlayerCombatEndPacket() {
    }
}
