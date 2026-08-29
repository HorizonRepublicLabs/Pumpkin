package net.minecraft.world.entity.ai.attributes;

import java.util.List;
import java.util.function.Consumer;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public class AttributeInstance {

    public AttributeInstance(Holder<Attribute> attribute, Consumer<AttributeInstance> onDirty) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeInstance.<init>:(Lnet/minecraft/core/Holder;Ljava/util/function/Consumer;)V");
    }

    public AttributeModifier getModifier(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeInstance.getModifier:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;");
    }

    public void addOrReplacePermanentModifier(AttributeModifier modifier) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeInstance.addOrReplacePermanentModifier:(Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;)V");
    }

    public void addPermanentModifier(AttributeModifier modifier) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeInstance.addPermanentModifier:(Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;)V");
    }

    public void removeModifier(AttributeModifier modifier) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeInstance.removeModifier:(Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;)V");
    }

    public boolean removeModifier(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeInstance.removeModifier:(Lnet/minecraft/resources/Identifier;)Z");
    }

    public double getValue() {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeInstance.getValue:()D");
    }

    public void apply(AttributeInstance.Packed packed) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeInstance.apply:(Lnet/minecraft/world/entity/ai/attributes/AttributeInstance$Packed;)V");
    }

    public record Packed(Holder<Attribute> attribute, double baseValue, List<AttributeModifier> modifiers) {
    }

    public AttributeInstance() {
    }
}
