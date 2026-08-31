package net.neoforged.neoforge.common.crafting;

import com.mojang.serialization.Codec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public final class SizedIngredient {

    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while

    // composing at class-init; null there is an NPE naming nothing. This survives

    // composition and throws on first real serialisation, naming the field.

    public static final Codec<SizedIngredient> NESTED_CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/neoforged/neoforge/common/crafting/SizedIngredient.NESTED_CODEC");

    public static final StreamCodec<RegistryFriendlyByteBuf, SizedIngredient> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    // Pumpkin divergence: really an ingredient plus a count; NeoForge's own bodies.
    private final Ingredient ingredient;

    private final int count;

    public SizedIngredient(Ingredient ingredient, int count) {
        this.ingredient = ingredient;
        this.count = count;
    }

    public Ingredient ingredient() {
        return ingredient;
    }

    public int count() {
        return count;
    }

    public boolean test(ItemStack stack) {
        return ingredient.test(stack) && stack.count() >= count;
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

    public SizedIngredient() {
        this(null, 0);
    }
}
