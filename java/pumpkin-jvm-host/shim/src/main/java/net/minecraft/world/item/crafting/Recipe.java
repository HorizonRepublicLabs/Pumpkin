package net.minecraft.world.item.crafting;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.function.BiFunction;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.display.RecipeDisplay;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public interface Recipe<T extends RecipeInput> {

    // Pumpkin divergence: a throwing codec, not null -- DFU composes through it
    // at class-init; it throws by name on first real use.
    Codec<Recipe<?>> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/item/crafting/Recipe.CODEC");

    StreamCodec<RegistryFriendlyByteBuf, Recipe<?>> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

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

        // Pumpkin divergence: real value, copied from vanilla. Entirely self-contained
        // over DataFixerUpper -- Codec.BOOL, this record's own accessor and constructor --
        // and DFU is a real library here, so this is vanilla's codec, not an imitation.
        // It was null, and DFU dereferenced it inside Cucumber's recipe classes: the same
        // silent-NPE side door as Identifier.CODEC, one commit earlier.
        public static final MapCodec<Recipe.CommonInfo> MAP_CODEC = com.mojang.serialization.codecs.RecordCodecBuilder.mapCodec(
            i -> i.group(com.mojang.serialization.Codec.BOOL.optionalFieldOf("show_notification", true).forGetter(Recipe.CommonInfo::showNotification)).apply(i, Recipe.CommonInfo::new)
        );

        public static final StreamCodec<RegistryFriendlyByteBuf, Recipe.CommonInfo> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");
    }
}
