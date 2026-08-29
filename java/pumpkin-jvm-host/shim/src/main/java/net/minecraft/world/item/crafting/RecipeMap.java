package net.minecraft.world.item.crafting;

import com.google.common.collect.Multimap;
import java.util.Collection;
import java.util.Map;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public class RecipeMap {

    private Multimap<RecipeType<?>, RecipeHolder<?>> byType;

    private RecipeMap(Multimap<RecipeType<?>, RecipeHolder<?>> byType, Map<ResourceKey<Recipe<?>>, RecipeHolder<?>> byKey) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeMap.<init>:(Lcom/google/common/collect/Multimap;Ljava/util/Map;)V");
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

    protected RecipeMap() {
    }
}
