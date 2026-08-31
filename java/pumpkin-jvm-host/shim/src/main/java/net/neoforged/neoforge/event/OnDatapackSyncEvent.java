package net.neoforged.neoforge.event;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public class OnDatapackSyncEvent extends Event {

    public OnDatapackSyncEvent(PlayerList playerList, ServerPlayer player) {
    }

    public PlayerList getPlayerList() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/OnDatapackSyncEvent.getPlayerList:()Lnet/minecraft/server/players/PlayerList;");
    }

    public ServerPlayer getPlayer() {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/OnDatapackSyncEvent.getPlayer:()Lnet/minecraft/server/level/ServerPlayer;");
    }

    public void sendRecipes(RecipeType<?>... recipeTypes) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/OnDatapackSyncEvent.sendRecipes:([Lnet/minecraft/world/item/crafting/RecipeType;)V");
    }

    public void sendRecipes(Iterable<RecipeType<?>> recipeTypes) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/OnDatapackSyncEvent.sendRecipes:(Ljava/lang/Iterable;)V");
    }

    public OnDatapackSyncEvent() {
    }
}
