package net.neoforged.neoforge.client.event;

import java.util.Set;
import net.minecraft.world.item.crafting.RecipeMap;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.Event;
import dev.pumpkin.shim.Unimplemented;

public class RecipesReceivedEvent extends Event {

    public RecipesReceivedEvent(Set<RecipeType<?>> recipeTypes, RecipeMap recipeMap) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RecipesReceivedEvent.<init>:(Ljava/util/Set;Lnet/minecraft/world/item/crafting/RecipeMap;)V");
    }

    public RecipeMap getRecipeMap() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/event/RecipesReceivedEvent.getRecipeMap:()Lnet/minecraft/world/item/crafting/RecipeMap;");
    }

    protected RecipesReceivedEvent() {
    }
}
