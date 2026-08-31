package net.minecraft.advancements;

import java.util.List;
import java.util.Optional;
import net.minecraft.commands.CacheableFunction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.storage.loot.LootTable;
import dev.pumpkin.shim.Unimplemented;

public record AdvancementRewards(int experience, List<ResourceKey<LootTable>> loot, List<ResourceKey<Recipe<?>>> recipes, Optional<CacheableFunction> function) {

    public static class Builder {

        public static AdvancementRewards.Builder recipe(ResourceKey<Recipe<?>> id) {
            throw Unimplemented.forMember("net/minecraft/advancements/AdvancementRewards$Builder.recipe:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/advancements/AdvancementRewards$Builder;");
        }

        public AdvancementRewards build() {
            throw Unimplemented.forMember("net/minecraft/advancements/AdvancementRewards$Builder.build:()Lnet/minecraft/advancements/AdvancementRewards;");
        }

        public Builder() {
        }
    }
}
