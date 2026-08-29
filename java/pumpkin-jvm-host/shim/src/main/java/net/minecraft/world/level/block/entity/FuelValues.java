package net.minecraft.world.level.block.entity;

import it.unimi.dsi.fastutil.objects.Object2IntSortedMap;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.TagKey;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import dev.pumpkin.shim.Unimplemented;

public class FuelValues {

    private FuelValues(Object2IntSortedMap<Item> values) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/FuelValues.<init>:(Lit/unimi/dsi/fastutil/objects/Object2IntSortedMap;)V");
    }

    public boolean isFuel(ItemStack itemStack) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/FuelValues.isFuel:(Lnet/minecraft/world/item/ItemStack;)Z");
    }

    public static class Builder {

        public Builder(HolderLookup.Provider registries, FeatureFlagSet enabledFeatures) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/FuelValues$Builder.<init>:(Lnet/minecraft/core/HolderLookup$Provider;Lnet/minecraft/world/flag/FeatureFlagSet;)V");
        }

        public FuelValues build() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/FuelValues$Builder.build:()Lnet/minecraft/world/level/block/entity/FuelValues;");
        }

        public FuelValues.Builder remove(TagKey<Item> tag) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/FuelValues$Builder.remove:(Lnet/minecraft/tags/TagKey;)Lnet/minecraft/world/level/block/entity/FuelValues$Builder;");
        }

        public FuelValues.Builder add(TagKey<Item> tag, int time) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/FuelValues$Builder.add:(Lnet/minecraft/tags/TagKey;I)Lnet/minecraft/world/level/block/entity/FuelValues$Builder;");
        }

        public FuelValues.Builder add(ItemLike itemLike, int time) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/FuelValues$Builder.add:(Lnet/minecraft/world/level/ItemLike;I)Lnet/minecraft/world/level/block/entity/FuelValues$Builder;");
        }

        public Builder() {
        }
    }

    public FuelValues() {
    }
}
