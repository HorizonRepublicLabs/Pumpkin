package net.minecraft.advancements.predicates;

import java.util.List;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Validatable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import dev.pumpkin.shim.Unimplemented;

public class ContextAwarePredicate implements Validatable {

    public ContextAwarePredicate(List<LootItemCondition> conditions) {
    }

    public static ContextAwarePredicate create(LootItemCondition... conditions) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/ContextAwarePredicate.create:([Lnet/minecraft/world/level/storage/loot/predicates/LootItemCondition;)Lnet/minecraft/advancements/predicates/ContextAwarePredicate;");
    }

    public boolean matches(LootContext context) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/ContextAwarePredicate.matches:(Lnet/minecraft/world/level/storage/loot/LootContext;)Z");
    }

    public void validate(ValidationContext context) {
        throw Unimplemented.forMember("net/minecraft/advancements/predicates/ContextAwarePredicate.validate:(Lnet/minecraft/world/level/storage/loot/ValidationContext;)V");
    }

    public ContextAwarePredicate() {
    }
}
