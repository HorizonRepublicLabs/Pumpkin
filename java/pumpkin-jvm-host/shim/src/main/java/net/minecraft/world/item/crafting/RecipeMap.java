package net.minecraft.world.item.crafting;

import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Stream;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class RecipeMap {

    // Pumpkin divergence: function-backed view over whatever resolver built it;
    // EMPTY is a real empty one. byType/getRecipesFor answer from the resolver.
    private java.util.function.Function<RecipeType<?>, Collection<RecipeHolder<?>>> pumpkinResolver;

    public static RecipeMap pumpkinOf(java.util.function.Function<RecipeType<?>, Collection<RecipeHolder<?>>> resolver) {
        RecipeMap map = new RecipeMap(null, null);
        map.pumpkinResolver = resolver;
        return map;
    }

    public static final RecipeMap EMPTY = pumpkinOf(type -> java.util.List.of());

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

    @SuppressWarnings({"unchecked", "rawtypes"})
    public <I extends RecipeInput, T extends Recipe<I>> Collection<RecipeHolder<T>> byType(RecipeType<T> type) {
        if (pumpkinResolver == null) {
            throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeMap.byType:(Lnet/minecraft/world/item/crafting/RecipeType;)Ljava/util/Collection;");
        }
        return (Collection) pumpkinResolver.apply(type);
    }

    public RecipeHolder<?> byKey(ResourceKey<Recipe<?>> recipeId) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeMap.byKey:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/item/crafting/RecipeHolder;");
    }

    public <I extends RecipeInput, T extends Recipe<I>> Stream<RecipeHolder<T>> getRecipesFor(RecipeType<T> type, I container, Level level) {
        return this.<I, T>byType(type).stream()
                .filter(holder -> holder.value().matches(container, level));
    }

    public RecipeMap() {
    }
}
