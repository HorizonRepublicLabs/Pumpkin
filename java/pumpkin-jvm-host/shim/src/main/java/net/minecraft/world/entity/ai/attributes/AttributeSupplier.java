package net.minecraft.world.entity.ai.attributes;

import java.util.Map;
import net.minecraft.core.Holder;
import dev.pumpkin.shim.Unimplemented;

public class AttributeSupplier {

    private AttributeSupplier(Map<Holder<Attribute>, AttributeInstance> instances) {
    }

    public double getValue(Holder<Attribute> attribute) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeSupplier.getValue:(Lnet/minecraft/core/Holder;)D");
    }

    public static class Builder {

        public Builder() {
        }

        public Builder(AttributeSupplier attributeMap) {
        }

        private AttributeInstance create(Holder<Attribute> attribute) {
            throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.create:(Lnet/minecraft/core/Holder;)Lnet/minecraft/world/entity/ai/attributes/AttributeInstance;");
        }

        public AttributeSupplier.Builder add(Holder<Attribute> attribute) {
            throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add:(Lnet/minecraft/core/Holder;)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;");
        }

        public AttributeSupplier.Builder add(Holder<Attribute> attribute, double baseValue) {
            throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.add:(Lnet/minecraft/core/Holder;D)Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder;");
        }

        public AttributeSupplier build() {
            throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeSupplier$Builder.build:()Lnet/minecraft/world/entity/ai/attributes/AttributeSupplier;");
        }
    }

    public AttributeSupplier() {
    }
}
