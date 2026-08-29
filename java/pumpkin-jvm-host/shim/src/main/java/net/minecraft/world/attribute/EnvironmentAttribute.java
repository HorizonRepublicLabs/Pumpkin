package net.minecraft.world.attribute;

import com.mojang.serialization.Codec;
import dev.pumpkin.shim.Unimplemented;

public class EnvironmentAttribute<Value> {

    private EnvironmentAttribute(AttributeType<Value> type, Value defaultValue, AttributeRange<Value> valueRange, boolean isSyncable, boolean isPositional, boolean isSpatiallyInterpolated) {
    }

    public AttributeType<Value> type() {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttribute.type:()Lnet/minecraft/world/attribute/AttributeType;");
    }

    public Codec<Value> valueCodec() {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttribute.valueCodec:()Lcom/mojang/serialization/Codec;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttribute.toString:()Ljava/lang/String;");
    }

    public static class Builder<Value> {

        public Builder(AttributeType<Value> type) {
        }

        public EnvironmentAttribute<Value> build() {
            throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttribute$Builder.build:()Lnet/minecraft/world/attribute/EnvironmentAttribute;");
        }

        public Builder() {
        }
    }

    public EnvironmentAttribute() {
    }
}
