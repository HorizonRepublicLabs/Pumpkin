package net.minecraft.world.item.crafting;

import com.mojang.serialization.DataResult;
import com.mojang.serialization.MapCodec;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public final class ShapedRecipePattern {

    public static int getMaxWidth() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipePattern.getMaxWidth:()I");
    }

    public static int getMaxHeight() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipePattern.getMaxHeight:()I");
    }

    public static final MapCodec<ShapedRecipePattern> MAP_CODEC = null;

    public static final StreamCodec<RegistryFriendlyByteBuf, ShapedRecipePattern> STREAM_CODEC = null;

    private final int width = 0;

    private final int height = 0;

    private final List<Optional<Ingredient>> ingredients = null;

    private final Optional<ShapedRecipePattern.Data> data = null;

    public ShapedRecipePattern(int width, int height, List<Optional<Ingredient>> ingredients, Optional<ShapedRecipePattern.Data> data) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipePattern.<init>:(IILjava/util/List;Ljava/util/Optional;)V");
    }

    public static ShapedRecipePattern of(Map<Character, Ingredient> key, String... pattern) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipePattern.of:(Ljava/util/Map;[Ljava/lang/String;)Lnet/minecraft/world/item/crafting/ShapedRecipePattern;");
    }

    public static ShapedRecipePattern of(Map<Character, Ingredient> key, List<String> pattern) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipePattern.of:(Ljava/util/Map;Ljava/util/List;)Lnet/minecraft/world/item/crafting/ShapedRecipePattern;");
    }

    private static DataResult<ShapedRecipePattern> unpack(ShapedRecipePattern.Data data) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipePattern.unpack:(Lnet/minecraft/world/item/crafting/ShapedRecipePattern$Data;)Lcom/mojang/serialization/DataResult;");
    }

    public boolean matches(CraftingInput input) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipePattern.matches:(Lnet/minecraft/world/item/crafting/CraftingInput;)Z");
    }

    private boolean matches(CraftingInput input, boolean xFlip) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipePattern.matches:(Lnet/minecraft/world/item/crafting/CraftingInput;Z)Z");
    }

    public int width() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipePattern.width:()I");
    }

    public int height() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipePattern.height:()I");
    }

    public List<Optional<Ingredient>> ingredients() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/ShapedRecipePattern.ingredients:()Ljava/util/List;");
    }

    public record Data(Map<Character, Ingredient> key, List<String> pattern) {
    }

    protected ShapedRecipePattern() {
    }
}
