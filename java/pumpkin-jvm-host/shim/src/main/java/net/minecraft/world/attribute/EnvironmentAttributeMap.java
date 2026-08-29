package net.minecraft.world.attribute;

import java.util.Map;
import java.util.Set;
import net.minecraft.world.attribute.modifier.AttributeModifier;
import dev.pumpkin.shim.Unimplemented;

public final class EnvironmentAttributeMap {

    private EnvironmentAttributeMap(Map<EnvironmentAttribute<?>, EnvironmentAttributeMap.Entry<?, ?>> entries) {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeMap.<init>:(Ljava/util/Map;)V");
    }

    public <Value> EnvironmentAttributeMap.Entry<Value, ?> get(EnvironmentAttribute<Value> attribute) {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeMap.get:(Lnet/minecraft/world/attribute/EnvironmentAttribute;)Lnet/minecraft/world/attribute/EnvironmentAttributeMap$Entry;");
    }

    public boolean contains(EnvironmentAttribute<?> attribute) {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeMap.contains:(Lnet/minecraft/world/attribute/EnvironmentAttribute;)Z");
    }

    public Set<EnvironmentAttribute<?>> keySet() {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeMap.keySet:()Ljava/util/Set;");
    }

    public boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeMap.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeMap.hashCode:()I");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeMap.toString:()Ljava/lang/String;");
    }

    public static class Builder {

        protected Builder() {
            throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeMap$Builder.<init>:()V");
        }

        public <Value, Parameter> EnvironmentAttributeMap.Builder modify(EnvironmentAttribute<Value> attribute, AttributeModifier<Value, Parameter> modifier, Parameter value) {
            throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeMap$Builder.modify:(Lnet/minecraft/world/attribute/EnvironmentAttribute;Lnet/minecraft/world/attribute/modifier/AttributeModifier;Ljava/lang/Object;)Lnet/minecraft/world/attribute/EnvironmentAttributeMap$Builder;");
        }

        public <Value> EnvironmentAttributeMap.Builder set(EnvironmentAttribute<Value> attribute, Value value) {
            throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeMap$Builder.set:(Lnet/minecraft/world/attribute/EnvironmentAttribute;Ljava/lang/Object;)Lnet/minecraft/world/attribute/EnvironmentAttributeMap$Builder;");
        }

        public EnvironmentAttributeMap build() {
            throw Unimplemented.forMember("net/minecraft/world/attribute/EnvironmentAttributeMap$Builder.build:()Lnet/minecraft/world/attribute/EnvironmentAttributeMap;");
        }
    }

    public record Entry<Value, Argument>(Argument argument, AttributeModifier<Value, Argument> modifier) {
    }

    public EnvironmentAttributeMap() {
    }
}
