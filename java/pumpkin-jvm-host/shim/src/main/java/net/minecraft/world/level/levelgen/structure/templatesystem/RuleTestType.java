package net.minecraft.world.level.levelgen.structure.templatesystem;

import com.mojang.serialization.MapCodec;
import dev.pumpkin.shim.Unimplemented;

public interface RuleTestType<P extends RuleTest> {

    MapCodec<P> codec();

    static <P extends RuleTest> RuleTestType<P> register(String id, MapCodec<P> codec) {
        throw Unimplemented.forMember("net/minecraft/world/level/levelgen/structure/templatesystem/RuleTestType.register:(Ljava/lang/String;Lcom/mojang/serialization/MapCodec;)Lnet/minecraft/world/level/levelgen/structure/templatesystem/RuleTestType;");
    }
}
