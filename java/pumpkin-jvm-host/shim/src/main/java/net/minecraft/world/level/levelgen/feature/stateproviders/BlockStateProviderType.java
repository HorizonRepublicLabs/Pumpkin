package net.minecraft.world.level.levelgen.feature.stateproviders;

import com.mojang.serialization.MapCodec;
import dev.pumpkin.shim.Unimplemented;

public class BlockStateProviderType<P extends BlockStateProvider> {

    private static <P extends BlockStateProvider> BlockStateProviderType<P> register(String name, MapCodec<P> codec) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/stateproviders/BlockStateProviderType.register:(Ljava/lang/String;Lcom/mojang/serialization/MapCodec;)Lnet/minecraft/world/level/levelgen/feature/stateproviders/BlockStateProviderType;");
    }

    public BlockStateProviderType(MapCodec<P> codec) {
    }

    public MapCodec<P> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/feature/stateproviders/BlockStateProviderType.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public BlockStateProviderType() {
    }
}
