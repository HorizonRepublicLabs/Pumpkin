package net.minecraft.world.level.storage.loot.functions;

import com.mojang.serialization.MapCodec;
import java.util.function.BiFunction;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootContextUser;

public interface LootItemFunction extends LootContextUser, BiFunction<ItemStack, LootContext, ItemStack> {

    MapCodec<? extends LootItemFunction> codec();

    interface Builder {

        LootItemFunction build();
    }
}
