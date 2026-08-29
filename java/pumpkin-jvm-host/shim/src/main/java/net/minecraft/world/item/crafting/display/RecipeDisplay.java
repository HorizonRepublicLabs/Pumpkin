package net.minecraft.world.item.crafting.display;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public interface RecipeDisplay {

    SlotDisplay result();

    SlotDisplay craftingStation();

    RecipeDisplay.Type<? extends RecipeDisplay> type();

    record Type<T extends RecipeDisplay>(MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
    }
}
