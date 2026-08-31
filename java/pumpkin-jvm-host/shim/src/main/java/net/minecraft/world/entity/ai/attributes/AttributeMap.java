package net.minecraft.world.entity.ai.attributes;

import java.util.List;
import net.minecraft.core.Holder;
import dev.pumpkin.shim.Unimplemented;

public class AttributeMap {

    public AttributeMap(AttributeSupplier supplier) {
    }

    public double getValue(Holder<Attribute> attribute) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeMap.getValue:(Lnet/minecraft/core/Holder;)D");
    }

    public void apply(List<AttributeInstance.Packed> packedAttributes) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeMap.apply:(Ljava/util/List;)V");
    }

    public AttributeMap() {
    }
}
