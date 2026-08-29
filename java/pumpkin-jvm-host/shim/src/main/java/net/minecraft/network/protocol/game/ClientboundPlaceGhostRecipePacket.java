package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundPlaceGhostRecipePacket(int containerId, RecipeDisplay recipeDisplay) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundPlaceGhostRecipePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlaceGhostRecipePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundPlaceGhostRecipePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
