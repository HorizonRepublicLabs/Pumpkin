package net.minecraft.world.level.levelgen.structure;

import com.mojang.serialization.MapCodec;
import dev.pumpkin.shim.Unimplemented;

public interface StructureType<S extends Structure> {

    MapCodec<S> codec();

    private static <S extends Structure> StructureType<S> register(String id, MapCodec<S> codec) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/StructureType.register:(Ljava/lang/String;Lcom/mojang/serialization/MapCodec;)Lnet/minecraft/world/level/levelgen/structure/StructureType;");
    }
}
