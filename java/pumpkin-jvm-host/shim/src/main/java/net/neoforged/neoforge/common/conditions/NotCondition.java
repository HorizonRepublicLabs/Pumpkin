package net.neoforged.neoforge.common.conditions;

import com.mojang.serialization.MapCodec;
import dev.pumpkin.shim.Unimplemented;

public record NotCondition(ICondition value) implements ICondition {

    public boolean test(IContext context) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/conditions/NotCondition.test:(Lnet/neoforged/neoforge/common/conditions/IContext;)Z");
    }

    public MapCodec<? extends ICondition> codec() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/conditions/NotCondition.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/conditions/NotCondition.toString:()Ljava/lang/String;");
    }
}
