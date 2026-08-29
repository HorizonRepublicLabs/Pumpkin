package net.minecraft.world.level.storage.loot.entries;

import com.mojang.serialization.MapCodec;
import java.util.List;
import net.minecraft.world.level.storage.loot.Validatable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.predicates.ConditionUserBuilder;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import dev.pumpkin.shim.Unimplemented;

public abstract class LootPoolEntryContainer implements ComposableEntryContainer, Validatable {

    protected LootPoolEntryContainer(List<LootItemCondition> conditions) {
    }

    public void validate(ValidationContext output) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/entries/LootPoolEntryContainer.validate:(Lnet/minecraft/world/level/storage/loot/ValidationContext;)V");
    }

    public abstract MapCodec<? extends LootPoolEntryContainer> codec();

    public abstract static class Builder<T extends LootPoolEntryContainer.Builder<T>> implements ConditionUserBuilder<T> {

        protected abstract T getThis();

        public T when(LootItemCondition.Builder condition) {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/entries/LootPoolEntryContainer$Builder.when:(Lnet/minecraft/world/level/storage/loot/predicates/LootItemCondition$Builder;)Lnet/minecraft/world/level/storage/loot/entries/LootPoolEntryContainer$Builder;");
        }

        public final T unwrap() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/entries/LootPoolEntryContainer$Builder.unwrap:()Lnet/minecraft/world/level/storage/loot/entries/LootPoolEntryContainer$Builder;");
        }

        public abstract LootPoolEntryContainer build();

        public Builder() {
        }
    }

    public LootPoolEntryContainer() {
    }
}
