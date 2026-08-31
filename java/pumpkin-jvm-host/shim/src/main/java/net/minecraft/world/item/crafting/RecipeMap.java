package net.minecraft.world.item.crafting;

import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class RecipeMap {

    public static final RecipeMap EMPTY = null;

    private Multimap<RecipeType<?>, RecipeHolder<?>> byType;

    private final Map<ResourceKey<Recipe<?>>, RecipeHolder<?>> byKey = null;

    private RecipeMap(Multimap<RecipeType<?>, RecipeHolder<?>> byType, Map<ResourceKey<Recipe<?>>, RecipeHolder<?>> byKey) {
    }

    public static RecipeMap create(Iterable<RecipeHolder<?>> recipes) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeMap.create:(Ljava/lang/Iterable;)Lnet/minecraft/world/item/crafting/RecipeMap;");
    }

    public void order(it.unimi.dsi.fastutil.objects.Object2IntMap<ResourceKey<net.minecraft.world.item.crafting.Recipe<?>>> recipePriorities) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeMap.order:(Lit/unimi/dsi/fastutil/objects/Object2IntMap;)V");
    }

    public <I extends RecipeInput, T extends Recipe<I>> Collection<RecipeHolder<T>> byType(RecipeType<T> type) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeMap.byType:(Lnet/minecraft/world/item/crafting/RecipeType;)Ljava/util/Collection;");
    }

    public RecipeHolder<?> byKey(ResourceKey<Recipe<?>> recipeId) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeMap.byKey:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/item/crafting/RecipeHolder;");
    }

    public <I extends RecipeInput, T extends Recipe<I>> Stream<RecipeHolder<T>> getRecipesFor(RecipeType<T> type, I container, Level level) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeMap.getRecipesFor:(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/item/crafting/RecipeInput;Lnet/minecraft/world/level/Level;)Ljava/util/stream/Stream;");
    }

    public RecipeMap() {
    }
}
