package net.minecraft.world.item.crafting.display;

import com.mojang.serialization.MapCodec;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.flag.FeatureFlagSet;
import dev.pumpkin.shim.Unimplemented;

public interface RecipeDisplay {

    SlotDisplay result();

    SlotDisplay craftingStation();

    RecipeDisplay.Type<? extends RecipeDisplay> type();

    default boolean isEnabled(FeatureFlagSet enabledFeatures) {
        throw Unimplemented.forMember("net/minecraft/world/item/crafting/display/RecipeDisplay.isEnabled:(Lnet/minecraft/world/flag/FeatureFlagSet;)Z");
    }

    record Type<T extends RecipeDisplay>(MapCodec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec) {
    }
}
