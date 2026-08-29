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

    public static final Codec<Ingredient> CODEC = null;

    private Ingredient(HolderSet<Item> values) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.<init>:(Lnet/minecraft/core/HolderSet;)V");
    }

    public Ingredient(net.neoforged.neoforge.common.crafting.ICustomIngredient customIngredient) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.<init>:(Lnet/neoforged/neoforge/common/crafting/ICustomIngredient;)V");
    }

    public Stream<Holder<Item>> items() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.items:()Ljava/util/stream/Stream;");
    }

    public boolean isEmpty() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.isEmpty:()Z");
    }

    public boolean test(ItemStack input) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.test:(Lnet/minecraft/world/item/ItemStack;)Z");
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

    public HolderSet<Item> getValues() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.getValues:()Lnet/minecraft/core/HolderSet;");
    }

    public boolean isSimple() {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.isSimple:()Z");
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
