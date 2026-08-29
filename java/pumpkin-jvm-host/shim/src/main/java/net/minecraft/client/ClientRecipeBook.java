package net.minecraft.client;

import net.minecraft.stats.RecipeBook;
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry;
import net.minecraft.world.item.crafting.display.RecipeDisplayId;
import dev.pumpkin.shim.Unimplemented;

public class ClientRecipeBook extends RecipeBook {

    public void add(RecipeDisplayEntry display) {
        throw Unimplemented.forMember("net/minecraft/client/ClientRecipeBook.add:(Lnet/minecraft/world/item/crafting/display/RecipeDisplayEntry;)V");
    }

    public void remove(RecipeDisplayId id) {
        throw Unimplemented.forMember("net/minecraft/client/ClientRecipeBook.remove:(Lnet/minecraft/world/item/crafting/display/RecipeDisplayId;)V");
    }

    public void clear() {
        throw Unimplemented.forMember("net/minecraft/client/ClientRecipeBook.clear:()V");
    }

    public ClientRecipeBook() {
    }
}
