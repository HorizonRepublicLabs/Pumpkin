package net.minecraft.world.item.alchemy;

import java.util.List;
import net.minecraft.core.Holder;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import dev.pumpkin.shim.Unimplemented;

public class PotionBrewing {

    private PotionBrewing(List<Ingredient> containers, List<PotionBrewing.Mix<Potion>> potionMixes, List<PotionBrewing.Mix<Item>> containerMixes) {
        throw Unimplemented.forMember("net/minecraft/world/item/alchemy/PotionBrewing.<init>:(Ljava/util/List;Ljava/util/List;Ljava/util/List;)V");
    }

    PotionBrewing(List<Ingredient> containers, List<PotionBrewing.Mix<Potion>> potionMixes, List<PotionBrewing.Mix<Item>> containerMixes, List<net.neoforged.neoforge.common.brewing.IBrewingRecipe> recipes) {
        throw Unimplemented.forMember("net/minecraft/world/item/alchemy/PotionBrewing.<init>:(Ljava/util/List;Ljava/util/List;Ljava/util/List;Ljava/util/List;)V");
    }

    public boolean isIngredient(ItemStack ingredient) {
        throw Unimplemented.forMember("net/minecraft/world/item/alchemy/PotionBrewing.isIngredient:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public boolean isInput(ItemStack stack) {
        throw Unimplemented.forMember("net/minecraft/world/item/alchemy/PotionBrewing.isInput:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public static class Builder {

        public Builder(FeatureFlagSet enabledFeatures) {
            throw Unimplemented.forMember("net/minecraft/world/item/alchemy/PotionBrewing$Builder.<init>:(Lnet/minecraft/world/flag/FeatureFlagSet;)V");
        }

        public PotionBrewing build() {
            throw Unimplemented.forMember("net/minecraft/world/item/alchemy/PotionBrewing$Builder.build:()Lnet/minecraft/world/item/alchemy/PotionBrewing;");
        }

        protected Builder() {
        }
    }

    private record Mix<T>(Holder<T> from, Ingredient ingredient, Holder<T> to) {
    }

    protected PotionBrewing() {
    }
}
