package net.minecraft.world.item.crafting;

import com.mojang.serialization.Codec;
import java.util.function.Predicate;
import java.util.stream.Stream;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.entity.player.StackedContents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.display.SlotDisplay;
import net.minecraft.world.level.ItemLike;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public final class Ingredient implements Predicate<ItemStack>, StackedContents.IngredientInfo<Holder<Item>> {

    public static final StreamCodec<RegistryFriendlyByteBuf, Ingredient> CONTENTS_STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while

    // composing at class-init; null there is an NPE naming nothing. This survives

    // composition and throws on first real serialisation, naming the field.

    // Pumpkin divergence: a real codec for the shapes mod recipes actually use -- a
    // plain item id, "#tag", or a list of either. NeoForge custom ingredient maps
    // (neoforge:ingredient_type) refuse with a reason, so a recipe using one fails its
    // decode loudly and is counted, never half-matched.
    public static final Codec<Ingredient> CODEC = new com.mojang.serialization.codecs.PrimitiveCodec<Ingredient>() {
        @Override
        public <T> com.mojang.serialization.DataResult<Ingredient> read(
                com.mojang.serialization.DynamicOps<T> ops, T input) {
            var asString = ops.getStringValue(input);
            if (asString.result().isPresent()) {
                return com.mojang.serialization.DataResult.success(
                        pumpkinOf(java.util.List.of(asString.result().get())));
            }
            var asList = ops.getStream(input);
            if (asList.result().isPresent()) {
                java.util.List<String> ids = new java.util.ArrayList<>();
                for (T entry : asList.result().get().toList()) {
                    var entryString = ops.getStringValue(entry);
                    if (entryString.result().isEmpty()) {
                        return com.mojang.serialization.DataResult.error(
                                () -> "unsupported ingredient entry (custom ingredient types are not decodable here)");
                    }
                    ids.add(entryString.result().get());
                }
                return com.mojang.serialization.DataResult.success(pumpkinOf(ids));
            }
            return com.mojang.serialization.DataResult.error(
                    () -> "unsupported ingredient shape (custom ingredient types are not decodable here)");
        }

        @Override
        public <T> T write(com.mojang.serialization.DynamicOps<T> ops, Ingredient value) {
            throw dev.pumpkin.shim.Unimplemented.forMember(
                    "net/minecraft/world/item/crafting/Ingredient.CODEC.encode");
        }
    };

    // Pumpkin divergence: the decoded item ids ("#..." entries are tags, kept but matched
    // never -- see test()).
    private java.util.List<String> pumpkinIds = java.util.List.of();

    // Pumpkin divergence: no vanilla counterpart -- the bridge synthesizes vanilla
    // cooking recipes and needs an ingredient over plain ids.
    public static Ingredient pumpkinOfIds(java.util.List<String> ids) {
        return pumpkinOf(ids);
    }

    private static Ingredient pumpkinOf(java.util.List<String> ids) {
        Ingredient ingredient = new Ingredient((HolderSet<Item>) null);
        ingredient.pumpkinIds = ids;
        return ingredient;
    }

    private Ingredient(HolderSet<Item> values) {
    }

    private boolean pumpkinCustom;

    public Ingredient(net.neoforged.neoforge.common.crafting.ICustomIngredient customIngredient) {
        this.pumpkinCustom = true;
    }

    public Stream<Holder<Item>> items() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.items:()Ljava/util/stream/Stream;");
    }

    public boolean isEmpty() {
        return pumpkinIds.isEmpty();
    }

    // Pumpkin divergence: real body over the decoded ids. A tag entry matches nothing
    // yet -- item tag membership for mod items is its own slice -- and says so once.
    public boolean test(ItemStack input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        String id = dev.pumpkin.bridge.PumpkinInteractions.pumpkinItemId(input);
        for (String candidate : pumpkinIds) {
            if (candidate.startsWith("#")) {
                if (dev.pumpkin.bridge.PumpkinTags.contains(candidate.substring(1), id)) {
                    return true;
                }
                continue;
            }
            if (candidate.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean acceptsItem(Holder<Item> item) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.acceptsItem:(Lnet/minecraft/core/Holder;)Z");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.hashCode:()I");
    }

    // Pumpkin divergence: real holders over the decoded ids, tag entries expanded
    // through the same tag tables test() consults.
    @SuppressWarnings("unchecked")
    public HolderSet<Item> getValues() {
        java.util.ArrayList<Holder<Item>> holders = new java.util.ArrayList<>();
        java.util.LinkedHashSet<String> ids = new java.util.LinkedHashSet<>();
        for (String candidate : pumpkinIds) {
            if (candidate.startsWith("#")) {
                ids.addAll(dev.pumpkin.bridge.PumpkinTags.itemMembers(candidate.substring(1)));
            } else {
                ids.add(candidate);
            }
        }
        for (String id : ids) {
            Item item = dev.pumpkin.bridge.PumpkinInteractions.pumpkinBuildStack(id, 1).getItem();
            holders.add((Holder<Item>) dev.pumpkin.shim.Stubs.of(Holder.class,
                    "net/minecraft/core/Holder(" + id + ")", java.util.Map.of("value", item)));
        }
        return HolderSet.direct(holders);
    }

    // Pumpkin divergence: NeoForge's own meaning -- simple unless custom logic hides
    // behind it.
    public boolean isSimple() {
        return !pumpkinCustom;
    }

    public net.neoforged.neoforge.common.crafting.ICustomIngredient getCustomIngredient() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.getCustomIngredient:()Lnet/neoforged/neoforge/common/crafting/ICustomIngredient;");
    }

    public boolean isCustom() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.isCustom:()Z");
    }

    public static Ingredient of(ItemLike itemLike) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.of:(Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public static Ingredient of(ItemLike... items) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.of:([Lnet/minecraft/world/level/ItemLike;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public static Ingredient of(Stream<? extends ItemLike> stream) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.of:(Ljava/util/stream/Stream;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public static Ingredient of(HolderSet<Item> tag) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.of:(Lnet/minecraft/core/HolderSet;)Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public SlotDisplay display() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.display:()Lnet/minecraft/world/item/crafting/display/SlotDisplay;");
    }

    public Ingredient() {
    }
}
