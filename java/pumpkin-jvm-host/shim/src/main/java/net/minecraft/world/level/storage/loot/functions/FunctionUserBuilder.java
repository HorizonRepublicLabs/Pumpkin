package net.minecraft.world.level.storage.loot.functions;

import java.util.function.Function;
import dev.pumpkin.shim.Unimplemented;

public interface FunctionUserBuilder<T extends FunctionUserBuilder<T>> {

    T apply(LootItemFunction.Builder builder);

    default <E> T apply(Iterable<E> collection, Function<E, LootItemFunction.Builder> functionProvider) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/functions/FunctionUserBuilder.apply:(Ljava/lang/Iterable;Ljava/util/function/Function;)Lnet/minecraft/world/level/storage/loot/functions/FunctionUserBuilder;");
    }

    default <E> T apply(E[] collection, Function<E, LootItemFunction.Builder> functionProvider) {
        throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/functions/FunctionUserBuilder.apply:([Ljava/lang/Object;Ljava/util/function/Function;)Lnet/minecraft/world/level/storage/loot/functions/FunctionUserBuilder;");
    }

    T unwrap();
}
