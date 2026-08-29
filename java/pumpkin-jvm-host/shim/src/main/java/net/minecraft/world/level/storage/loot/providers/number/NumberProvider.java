package net.minecraft.world.level.storage.loot.providers.number;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootContextUser;
import dev.pumpkin.shim.Unimplemented;

public interface NumberProvider extends LootContextUser {

    float getFloat(LootContext context);

    default int getInt(LootContext context) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/providers/number/NumberProvider.getInt:(Lnet/minecraft/world/level/storage/loot/LootContext;)I");
    }

    MapCodec<? extends NumberProvider> codec();
}
