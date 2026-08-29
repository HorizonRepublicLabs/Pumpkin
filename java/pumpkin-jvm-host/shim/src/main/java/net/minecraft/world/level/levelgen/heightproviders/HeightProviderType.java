package net.minecraft.world.level.levelgen.heightproviders;

import com.mojang.serialization.MapCodec;
import dev.pumpkin.shim.Unimplemented;

public interface HeightProviderType<P extends HeightProvider> {

    MapCodec<P> codec();

    private static <P extends HeightProvider> HeightProviderType<P> register(String id, MapCodec<P> codec) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/heightproviders/HeightProviderType.register:(Ljava/lang/String;Lcom/mojang/serialization/MapCodec;)Lnet/minecraft/world/level/levelgen/heightproviders/HeightProviderType;");
    }
}
