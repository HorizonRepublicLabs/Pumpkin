package net.minecraft.world.attribute;

import com.mojang.serialization.Codec;
import java.util.Map;
import net.minecraft.util.ToFloatFunction;
import net.minecraft.world.attribute.modifier.AttributeModifier;
import dev.pumpkin.shim.Unimplemented;

public record AttributeType<Value>(Codec<Value> valueCodec, Map<AttributeModifier.OperationId, AttributeModifier<Value, ?>> modifierLibrary, Codec<AttributeModifier<Value, ?>> modifierCodec, LerpFunction<Value> keyframeLerp, LerpFunction<Value> stateChangeLerp, LerpFunction<Value> spatialLerp, LerpFunction<Value> partialTickLerp, ToFloatFunction<Value> toFloat) {

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/attribute/AttributeType.toString:()Ljava/lang/String;");
    }
}
