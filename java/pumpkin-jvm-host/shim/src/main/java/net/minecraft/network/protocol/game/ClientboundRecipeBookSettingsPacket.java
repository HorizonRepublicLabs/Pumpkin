package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.stats.RecipeBookSettings;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundRecipeBookSettingsPacket(RecipeBookSettings bookSettings) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundRecipeBookSettingsPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundRecipeBookSettingsPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundRecipeBookSettingsPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
