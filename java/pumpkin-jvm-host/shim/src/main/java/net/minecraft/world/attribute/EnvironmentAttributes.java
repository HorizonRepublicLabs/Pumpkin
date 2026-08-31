package net.minecraft.world.attribute;

import dev.pumpkin.shim.Unimplemented;

public interface EnvironmentAttributes {

    EnvironmentAttribute<Float> WATER_FOG_START_DISTANCE = null;

    EnvironmentAttribute<Float> WATER_FOG_END_DISTANCE = null;

    EnvironmentAttribute<Float> SKY_LIGHT_FACTOR = null;

    private static <Value> EnvironmentAttribute<Value> register(String id, EnvironmentAttribute.Builder<Value> attributeBuilder) {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributes.register:(Ljava/lang/String;Lnet/minecraft/world/attribute/EnvironmentAttribute$Builder;)Lnet/minecraft/world/attribute/EnvironmentAttribute;");
    }
}
