package net.minecraft.world.level.levelgen.blockpredicates;

import com.mojang.serialization.MapCodec;
import dev.pumpkin.shim.Unimplemented;

public interface BlockPredicateType<P extends BlockPredicate> {

    MapCodec<P> codec();

    private static <P extends BlockPredicate> BlockPredicateType<P> register(String id, MapCodec<P> codec) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/blockpredicates/BlockPredicateType.register:(Ljava/lang/String;Lcom/mojang/serialization/MapCodec;)Lnet/minecraft/world/level/levelgen/blockpredicates/BlockPredicateType;");
    }
}
