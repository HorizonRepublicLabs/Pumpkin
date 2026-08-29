package net.minecraft.world.entity.ai.attributes;

import net.minecraft.resources.Identifier;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public record AttributeModifier(Identifier id, double amount, AttributeModifier.Operation operation) {

    public boolean is(Identifier id) {
        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeModifier.is:(Lnet/minecraft/resources/Identifier;)Z");
    }

    public enum Operation implements StringRepresentable {

        ADD_VALUE, ADD_MULTIPLIED_BASE, ADD_MULTIPLIED_TOTAL;

        public int id() {
            throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeModifier$Operation.id:()I");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/AttributeModifier$Operation.getSerializedName:()Ljava/lang/String;");
        }
    }
}
