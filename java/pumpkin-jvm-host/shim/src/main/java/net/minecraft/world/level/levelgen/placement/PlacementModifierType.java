package net.minecraft.world.level.levelgen.placement;

import com.mojang.serialization.MapCodec;
import dev.pumpkin.shim.Unimplemented;

public interface PlacementModifierType<P extends PlacementModifier> {

    MapCodec<P> codec();

    private static <P extends PlacementModifier> PlacementModifierType<P> register(String id, MapCodec<P> codec) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/placement/PlacementModifierType.register:(Ljava/lang/String;Lcom/mojang/serialization/MapCodec;)Lnet/minecraft/world/level/levelgen/placement/PlacementModifierType;");
    }
}
