package net.minecraft.world.attribute;

import java.util.List;
import java.util.Map;
import net.minecraft.world.phys.Vec3;
import dev.pumpkin.shim.Unimplemented;

public class EnvironmentAttributeSystem implements EnvironmentAttributeReader {

    private EnvironmentAttributeSystem(Map<EnvironmentAttribute<?>, List<EnvironmentAttributeLayer<?>>> layersByAttribute) {
    }

    public <Value> Value getDimensionValue(EnvironmentAttribute<Value> attribute) {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeSystem.getDimensionValue:(Lnet/minecraft/world/attribute/EnvironmentAttribute;)Ljava/lang/Object;");
    }

    public <Value> Value getValue(EnvironmentAttribute<Value> attribute, Vec3 pos, SpatialAttributeInterpolator biomeInterpolator) {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeSystem.getValue:(Lnet/minecraft/world/attribute/EnvironmentAttribute;Lnet/minecraft/world/phys/Vec3;Lnet/minecraft/world/attribute/SpatialAttributeInterpolator;)Ljava/lang/Object;");
    }

    public static class Builder {

        protected Builder() {
        }

        public EnvironmentAttributeSystem build() {
            throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeSystem$Builder.build:()Lnet/minecraft/world/attribute/EnvironmentAttributeSystem;");
        }
    }

    private static class ValueSampler<Value> {

        private ValueSampler(EnvironmentAttribute<Value> attribute, Value baseValue, List<EnvironmentAttributeLayer<Value>> layers, boolean isAffectedByPosition) {
        }

        protected ValueSampler() {
        }
    }

    public EnvironmentAttributeSystem() {
    }
}
