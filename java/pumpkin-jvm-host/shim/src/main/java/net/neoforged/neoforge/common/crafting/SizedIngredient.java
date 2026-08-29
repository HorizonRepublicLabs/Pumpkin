package net.neoforged.neoforge.common.crafting;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import dev.pumpkin.shim.Unimplemented;

public final class SizedIngredient {

    public static final Codec<SizedIngredient> NESTED_CODEC = null;

    public static final StreamCodec<RegistryFriendlyByteBuf, SizedIngredient> STREAM_CODEC = null;

    private final Ingredient ingredient = null;

    private final int count = 0;

    public SizedIngredient(Ingredient ingredient, int count) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/SizedIngredient.<init>:(Lnet/minecraft/world/item/crafting/Ingredient;I)V");
    }

    public Ingredient ingredient() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/SizedIngredient.ingredient:()Lnet/minecraft/world/item/crafting/Ingredient;");
    }

    public int count() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/SizedIngredient.count:()I");
    }

    public boolean test(ItemStack stack) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/SizedIngredient.test:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/SizedIngredient.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/SizedIngredient.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/SizedIngredient.toString:()Ljava/lang/String;");
    }

    protected SizedIngredient() {
    }
}
