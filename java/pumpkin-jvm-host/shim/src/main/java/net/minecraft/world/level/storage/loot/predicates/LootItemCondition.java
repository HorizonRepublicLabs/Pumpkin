package net.minecraft.world.level.storage.loot.predicates;

import com.mojang.serialization.MapCodec;
import java.util.function.Predicate;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootContextUser;

public interface LootItemCondition extends LootContextUser, Predicate<LootContext> {

    MapCodec<? extends LootItemCondition> codec();

    interface Builder {

        LootItemCondition build();
    }
}
