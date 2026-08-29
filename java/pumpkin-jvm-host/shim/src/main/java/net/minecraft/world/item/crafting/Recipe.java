package net.minecraft.world.item.crafting;

import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.function.BiFunction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public interface Recipe<T extends RecipeInput> {

    StreamCodec<RegistryFriendlyByteBuf, Recipe<?>> STREAM_CODEC = null;

    boolean matches(T input, Level level);

    ItemStack assemble(T input);

    default boolean isSpecial() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Recipe.isSpecial:()Z");
    }

    boolean showNotification();

    String group();

    RecipeSerializer<? extends Recipe<T>> getSerializer();

    RecipeType<? extends Recipe<T>> getType();

    PlacementInfo placementInfo();

    default List<RecipeDisplay> display() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Recipe.display:()Ljava/util/List;");
    }

    RecipeBookCategory recipeBookCategory();

    interface BookInfo<CategoryType> {

        CategoryType category();

        String group();

        interface Constructor<CategoryType, SelfType extends Recipe.BookInfo<CategoryType>> extends BiFunction<CategoryType, String, SelfType> {
        }
    }

    record CommonInfo(boolean showNotification) {

        public static final MapCodec<Recipe.CommonInfo> MAP_CODEC = null;

        public static final StreamCodec<RegistryFriendlyByteBuf, Recipe.CommonInfo> STREAM_CODEC = null;
    }
}
