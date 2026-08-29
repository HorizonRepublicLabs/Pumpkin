package net.minecraft.world.level.storage.loot.predicates;

public interface ConditionUserBuilder<T extends ConditionUserBuilder<T>> {

    T when(final LootItemCondition.Builder builder);

    T unwrap();
}
