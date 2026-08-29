package net.minecraft.world.level.storage.loot;

import java.util.List;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.FunctionUserBuilder;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.predicates.ConditionUserBuilder;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import dev.pumpkin.shim.Unimplemented;

public class LootPool implements Validatable {

    private LootPool(List<LootPoolEntryContainer> entries, List<LootItemCondition> conditions, List<LootItemFunction> functions, NumberProvider rolls, NumberProvider bonusRolls, java.util.Optional<String> name) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootPool.<init>:(Ljava/util/List;Ljava/util/List;Ljava/util/List;Lnet/minecraft/world/level/storage/loot/providers/number/NumberProvider;Lnet/minecraft/world/level/storage/loot/providers/number/NumberProvider;Ljava/util/Optional;)V");
    }

    public void validate(ValidationContext output) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootPool.validate:(Lnet/minecraft/world/level/storage/loot/ValidationContext;)V");
    }

    public void freeze() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootPool.freeze:()V");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootPool.getName:()Ljava/lang/String;");
    }

    public static class Builder implements FunctionUserBuilder<LootPool.Builder>, ConditionUserBuilder<LootPool.Builder> {

        public LootPool.Builder unwrap() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootPool$Builder.unwrap:()Lnet/minecraft/world/level/storage/loot/LootPool$Builder;");
        }

        public LootPool.Builder add(LootPoolEntryContainer.Builder<?> entry) {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootPool$Builder.add:(Lnet/minecraft/world/level/storage/loot/entries/LootPoolEntryContainer$Builder;)Lnet/minecraft/world/level/storage/loot/LootPool$Builder;");
        }

        public LootPool.Builder when(LootItemCondition.Builder condition) {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootPool$Builder.when:(Lnet/minecraft/world/level/storage/loot/predicates/LootItemCondition$Builder;)Lnet/minecraft/world/level/storage/loot/LootPool$Builder;");
        }

        public LootPool.Builder apply(LootItemFunction.Builder function) {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootPool$Builder.apply:(Lnet/minecraft/world/level/storage/loot/functions/LootItemFunction$Builder;)Lnet/minecraft/world/level/storage/loot/LootPool$Builder;");
        }

        public LootPool build() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootPool$Builder.build:()Lnet/minecraft/world/level/storage/loot/LootPool;");
        }

        public Builder() {
        }
    }

    public LootPool() {
    }
}
