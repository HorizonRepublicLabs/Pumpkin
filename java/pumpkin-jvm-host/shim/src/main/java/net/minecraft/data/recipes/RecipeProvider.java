package net.minecraft.data.recipes;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public abstract class RecipeProvider {

    protected final RecipeOutput output = Stubs.of(RecipeOutput.class, "net/minecraft/data/recipes/RecipeOutput");

    protected RecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
    }

    protected abstract void buildRecipes();

    protected Ingredient tag(TagKey<Item> id) {
        throw Unimplemented.forMember("net/minecraft/data/recipes/RecipeProvider.tag:(Lnet/minecraft/tags/TagKey;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    private interface FamilyCraftingRecipeProvider {

        RecipeBuilder create(RecipeProvider context, ItemLike result, ItemLike base);
    }

    private interface FamilyStonecutterRecipeProvider {

        void create(RecipeProvider context, ItemLike result, ItemLike base);
    }

    public abstract static class Runner implements DataProvider {

        protected Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
        }

        public final CompletableFuture<?> run(CachedOutput cache) {
            throw Unimplemented.forMember("net/minecraft/data/recipes/RecipeProvider$Runner.run:(Lnet/minecraft/data/CachedOutput;)Ljava/util/concurrent/CompletableFuture;");
        }

        protected abstract RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output);

        public Runner() {
        }
    }

    public RecipeProvider() {
    }
}
