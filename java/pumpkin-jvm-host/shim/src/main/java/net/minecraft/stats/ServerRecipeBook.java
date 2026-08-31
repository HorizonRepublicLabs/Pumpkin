package net.minecraft.stats;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry;
import dev.pumpkin.shim.Unimplemented;

public class ServerRecipeBook extends RecipeBook {

    public ServerRecipeBook(ServerRecipeBook.DisplayResolver displayResolver) {
    }

    public void add(ResourceKey<Recipe<?>> id) {
        throw Unimplemented.forMember("net/minecraft/stats/ServerRecipeBook.add:(Lnet/minecraft/resources/ResourceKey;)V");
    }

    public boolean contains(ResourceKey<Recipe<?>> id) {
        throw Unimplemented.forMember("net/minecraft/stats/ServerRecipeBook.contains:(Lnet/minecraft/resources/ResourceKey;)Z");
    }

    public void remove(ResourceKey<Recipe<?>> id) {
        throw Unimplemented.forMember("net/minecraft/stats/ServerRecipeBook.remove:(Lnet/minecraft/resources/ResourceKey;)V");
    }

    private void apply(ServerRecipeBook.Packed packed) {
        throw Unimplemented.forMember("net/minecraft/stats/ServerRecipeBook.apply:(Lnet/minecraft/stats/ServerRecipeBook$Packed;)V");
    }

    public interface DisplayResolver {

        void displaysForRecipe(ResourceKey<Recipe<?>> id, Consumer<RecipeDisplayEntry> output);
    }

    public record Packed(RecipeBookSettings settings, List<ResourceKey<Recipe<?>>> known, List<ResourceKey<Recipe<?>>> highlight) {
    }

    public ServerRecipeBook() {
    }
}
