package net.neoforged.neoforge.common.crafting;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Unimplemented;

public record IngredientType<T extends ICustomIngredient>(MapCodec<T> codec, StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {

    public IngredientType(MapCodec<T> codec) {
        this((MapCodec<T>) null, (StreamCodec<? super RegistryFriendlyByteBuf, T>) null);
        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/IngredientType.<init>:(Lcom/mojang/serialization/MapCodec;)V");
    }
}
