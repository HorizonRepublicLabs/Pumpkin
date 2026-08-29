package net.minecraft.world.item.crafting;

import java.util.Optional;
import java.util.function.Consumer;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimplePreparableReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.crafting.display.RecipeDisplayEntry;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class RecipeManager extends SimplePreparableReloadListener<RecipeMap> implements RecipeAccess {

    private final HolderLookup.Provider registries = null;

    public RecipeManager(HolderLookup.Provider registries) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeManager.<init>:(Lnet/minecraft/core/HolderLookup$Provider;)V");
    }

    protected RecipeMap prepare(ResourceManager manager, ProfilerFiller profiler) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeManager.prepare:(Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)Lnet/minecraft/world/item/crafting/RecipeMap;");
    }

    protected void apply(RecipeMap recipes, ResourceManager manager, ProfilerFiller profiler) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeManager.apply:(Lnet/minecraft/world/item/crafting/RecipeMap;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V");
    }

    public <I extends RecipeInput, T extends Recipe<I>> Optional<RecipeHolder<T>> getRecipeFor(RecipeType<T> type, I input, Level level, ResourceKey<Recipe<?>> recipeHint) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeManager.getRecipeFor:(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/item/crafting/RecipeInput;Lnet/minecraft/world/level/Level;Lnet/minecraft/resources/ResourceKey;)Ljava/util/Optional;");
    }

    public <I extends RecipeInput, T extends Recipe<I>> Optional<RecipeHolder<T>> getRecipeFor(RecipeType<T> type, I input, Level level, RecipeHolder<T> recipeHint) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeManager.getRecipeFor:(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/item/crafting/RecipeInput;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/item/crafting/RecipeHolder;)Ljava/util/Optional;");
    }

    public <I extends RecipeInput, T extends Recipe<I>> Optional<RecipeHolder<T>> getRecipeFor(RecipeType<T> type, I input, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeManager.getRecipeFor:(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/item/crafting/RecipeInput;Lnet/minecraft/world/level/Level;)Ljava/util/Optional;");
    }

    public RecipePropertySet propertySet(ResourceKey<RecipePropertySet> id) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeManager.propertySet:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/item/crafting/RecipePropertySet;");
    }

    public SelectableRecipe.SingleInputSet<StonecutterRecipe> stonecutterRecipes() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeManager.stonecutterRecipes:()Lnet/minecraft/world/item/crafting/SelectableRecipe$SingleInputSet;");
    }

    public RecipeMap recipeMap() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeManager.recipeMap:()Lnet/minecraft/world/item/crafting/RecipeMap;");
    }

    public interface CachedCheck<I extends RecipeInput, T extends Recipe<I>> {

        Optional<RecipeHolder<T>> getRecipeFor(I input, ServerLevel level);
    }

    public static class IngredientCollector implements Consumer<Recipe<?>> {

        protected IngredientCollector(ResourceKey<RecipePropertySet> key, RecipeManager.IngredientExtractor extractor) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeManager$IngredientCollector.<init>:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/item/crafting/RecipeManager$IngredientExtractor;)V");
        }

        public void accept(Recipe<?> recipe) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeManager$IngredientCollector.accept:(Lnet/minecraft/world/item/crafting/Recipe;)V");
        }

        protected IngredientCollector() {
        }
    }

    public interface IngredientExtractor {

        Optional<Ingredient> apply(Recipe<?> recipe);
    }

    public record ServerDisplayInfo(RecipeDisplayEntry display, RecipeHolder<?> parent) {
    }

    protected RecipeManager() {
    }
}
