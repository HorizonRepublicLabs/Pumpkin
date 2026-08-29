package net.minecraft.network.protocol.game;

import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import dev.pumpkin.shim.Unimplemented;

public record ServerboundPlaceRecipePacket(int containerId, RecipeDisplayId recipe, boolean useMaxItems) implements Packet<ServerGamePacketListener> {

    public PacketType<ServerboundPlaceRecipePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlaceRecipePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ServerGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ServerboundPlaceRecipePacket.handle:(Lnet/minecraft/network/protocol/game/ServerGamePacketListener;)V");
    }
}
