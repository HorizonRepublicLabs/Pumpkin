package net.minecraft.advancements.triggers;

import com.mojang.serialization.Codec;
import java.util.Optional;
import net.minecraft.advancements.predicates.ContextAwarePredicate;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeHolder;
import dev.pumpkin.shim.Unimplemented;

public class RecipeUnlockedTrigger extends SimpleCriterionTrigger<RecipeUnlockedTrigger.TriggerInstance> {

    public Codec<RecipeUnlockedTrigger.TriggerInstance> codec() {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/RecipeUnlockedTrigger.codec:()Lcom/mojang/serialization/Codec;");
    }

    public static Criterion<RecipeUnlockedTrigger.TriggerInstance> unlocked(ResourceKey<Recipe<?>> recipe) {
        throw Unimplemented.forMember("net/minecraft/advancements/triggers/RecipeUnlockedTrigger.unlocked:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/advancements/triggers/Criterion;");
    }

    public record TriggerInstance(Optional<ContextAwarePredicate> player, ResourceKey<Recipe<?>> recipe) implements SimpleCriterionTrigger.SimpleInstance {

        public boolean matches(RecipeHolder<?> recipe) {
            throw Unimplemented.forMember("net/minecraft/advancements/triggers/RecipeUnlockedTrigger$TriggerInstance.matches:(Lnet/minecraft/world/item/crafting/RecipeHolder;)Z");
        }
    }

    public RecipeUnlockedTrigger() {
    }
}
