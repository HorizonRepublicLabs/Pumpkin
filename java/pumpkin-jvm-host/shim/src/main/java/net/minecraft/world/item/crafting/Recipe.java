package net.minecraft.world.item.crafting;

import com.mojang.serialization.MapCodec;
import java.util.function.BiFunction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public interface Recipe<T extends RecipeInput> {

    boolean matches(T input, Level level);

    ItemStack assemble(T input);

    boolean showNotification();

    String group();

    RecipeSerializer<? extends Recipe<T>> getSerializer();

    RecipeType<? extends Recipe<T>> getType();

    PlacementInfo placementInfo();

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
