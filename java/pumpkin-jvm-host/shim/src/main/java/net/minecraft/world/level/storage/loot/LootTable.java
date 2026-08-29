package net.minecraft.world.level.storage.loot;

import java.util.List;
import java.util.Optional;
import net.minecraft.resources.Identifier;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.Container;
import net.minecraft.world.level.storage.loot.functions.FunctionUserBuilder;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import dev.pumpkin.shim.Unimplemented;

public class LootTable implements Validatable {

    private LootTable(ContextKeySet paramSet, Optional<Identifier> randomSequence, List<LootPool> pools, List<LootItemFunction> functions) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootTable.<init>:(Lnet/minecraft/util/context/ContextKeySet;Ljava/util/Optional;Ljava/util/List;Ljava/util/List;)V");
    }

    public void validate(ValidationContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootTable.validate:(Lnet/minecraft/world/level/storage/loot/ValidationContext;)V");
    }

    public void fill(Container container, LootParams params, long optionalRandomSeed) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootTable.fill:(Lnet/minecraft/world/Container;Lnet/minecraft/world/level/storage/loot/LootParams;J)V");
    }

    public void freeze() {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootTable.freeze:()V");
    }

    public static class Builder implements FunctionUserBuilder<LootTable.Builder> {

        public LootTable.Builder apply(LootItemFunction.Builder function) {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootTable$Builder.apply:(Lnet/minecraft/world/level/storage/loot/functions/LootItemFunction$Builder;)Lnet/minecraft/world/level/storage/loot/LootTable$Builder;");
        }

        public LootTable.Builder unwrap() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootTable$Builder.unwrap:()Lnet/minecraft/world/level/storage/loot/LootTable$Builder;");
        }

        public LootTable build() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootTable$Builder.build:()Lnet/minecraft/world/level/storage/loot/LootTable;");
        }

        public Builder() {
        }
    }

    public LootTable() {
    }
}
