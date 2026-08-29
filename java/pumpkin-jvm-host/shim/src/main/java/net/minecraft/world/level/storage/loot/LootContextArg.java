package net.minecraft.world.level.storage.loot;

import com.mojang.serialization.Codec;
import net.minecraft.util.context.ContextKey;
import dev.pumpkin.shim.Unimplemented;

public interface LootContextArg<R> {

    R get(LootContext context);

    ContextKey<?> contextParam();

    final class ArgCodecBuilder<R> {

        protected ArgCodecBuilder() {
        }

        private Codec<LootContextArg<R>> build() {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContextArg$ArgCodecBuilder.build:()Lcom/mojang/serialization/Codec;");
        }
    }

    interface Getter<T, R> extends LootContextArg<R> {

        R get(T value);

        ContextKey<? extends T> contextParam();

        default R get(LootContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContextArg$Getter.get:(Lnet/minecraft/world/level/storage/loot/LootContext;)Ljava/lang/Object;");
        }
    }

    interface SimpleGetter<T> extends LootContextArg<T> {

        ContextKey<? extends T> contextParam();

        default T get(LootContext context) {
            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/LootContextArg$SimpleGetter.get:(Lnet/minecraft/world/level/storage/loot/LootContext;)Ljava/lang/Object;");
        }
    }
}
