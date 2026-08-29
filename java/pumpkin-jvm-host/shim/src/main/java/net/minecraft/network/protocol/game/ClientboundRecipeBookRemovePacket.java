package net.minecraft.network.protocol.game;

import java.util.List;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundRecipeBookRemovePacket(List<RecipeDisplayId> recipes) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundRecipeBookRemovePacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundRecipeBookRemovePacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundRecipeBookRemovePacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
