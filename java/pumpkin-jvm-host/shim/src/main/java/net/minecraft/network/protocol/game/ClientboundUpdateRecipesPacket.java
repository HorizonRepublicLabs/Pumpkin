package net.minecraft.network.protocol.game;

import java.util.Map;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.PacketType;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.RecipePropertySet;
import net.minecraft.world.item.crafting.SelectableRecipe;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import dev.pumpkin.shim.Unimplemented;

public record ClientboundUpdateRecipesPacket(Map<ResourceKey<RecipePropertySet>, RecipePropertySet> itemSets, SelectableRecipe.SingleInputSet<StonecutterRecipe> stonecutterRecipes) implements Packet<ClientGamePacketListener> {

    public PacketType<ClientboundUpdateRecipesPacket> type() {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateRecipesPacket.type:()Lnet/minecraft/network/protocol/PacketType;");
    }

    public void handle(ClientGamePacketListener listener) {
        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundUpdateRecipesPacket.handle:(Lnet/minecraft/network/protocol/game/ClientGamePacketListener;)V");
    }
}
