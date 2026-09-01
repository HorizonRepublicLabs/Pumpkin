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
    }

    // Pumpkin divergence: answered from the vanilla fuel table on the Rust side --
    // the same table a furnace burns from. Mod items are not in it and honestly
    // answer 0 unless the mod overrides Item.getBurnTime.
    public int burnDuration(ItemStack itemStack) {
        if (itemStack == null || itemStack.isEmpty()) {
            return 0;
        }
        String id = dev.pumpkin.bridge.PumpkinInteractions.pumpkinItemId(itemStack);
        try {
            int ticks = (Integer) Class.forName("dev.pumpkin.jvmhost.PumpkinHost")
                    .getMethod("vanillaBurnTicks", String.class).invoke(null, id);            return ticks;
        } catch (ReflectiveOperationException e) {
            System.err.println("[pumpkin] vanilla fuel lookup for " + id + " failed: " + e);
            return 0;
        }
    }

    public boolean isFuel(ItemStack itemStack) {
        return burnDuration(itemStack) > 0;
    }

    // Pumpkin divergence: no vanilla counterpart -- the one instance the stand-in
    // level hands out.
    private static final FuelValues PUMPKIN_VANILLA = new FuelValues();

    public static FuelValues pumpkinVanilla() {
        return PUMPKIN_VANILLA;
    }

    public static class Builder {

        public Builder(HolderLookup.Provider registries, FeatureFlagSet enabledFeatures) {
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
