package net.minecraft.network.protocol.game;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.entity.player.Abilities;
import dev.pumpkin.shim.Unimplemented;

public class ClientboundPlayerAbilitiesPacket implements Packet<ClientGamePacketListener> {

    public ClientboundPlayerAbilitiesPacket(Abilities abilities) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerAbilitiesPacket.<init>:(Lnet/minecraft/world/entity/player/Abilities;)V");
    }

    private ClientboundPlayerAbilitiesPacket(FriendlyByteBuf input) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerAbilitiesPacket.<init>:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    private void write(FriendlyByteBuf output) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerAbilitiesPacket.write:(Lnet/minecraft/network/FriendlyByteBuf;)V");
    }

    public PacketType<ClientboundPlayerAbilitiesPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerAbilitiesPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlayerAbilitiesPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }

    protected ClientboundPlayerAbilitiesPacket() {
    }
}
