package net.minecraft.world.level.levelgen.structure.pools;

import com.mojang.serialization.MapCodec;
import dev.pumpkin.shim.Unimplemented;

public interface StructurePoolElementType<P extends StructurePoolElement> {

    MapCodec<P> codec();

    static <P extends StructurePoolElement> StructurePoolElementType<P> register(String id, MapCodec<P> codec) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/pools/StructurePoolElementType.register:(Ljava/lang/String;Lcom/mojang/serialization/MapCodec;)Lnet/minecraft/world/level/levelgen/structure/pools/StructurePoolElementType;");
    }
}
