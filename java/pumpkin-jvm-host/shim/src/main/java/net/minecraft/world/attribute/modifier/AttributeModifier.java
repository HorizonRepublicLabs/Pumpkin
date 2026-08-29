package net.minecraft.world.attribute.modifier;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.attribute.EnvironmentAttribute;
import net.minecraft.world.attribute.LerpFunction;
import dev.pumpkin.shim.Unimplemented;

public interface AttributeModifier<Subject, Argument> {

    Subject apply(Subject subject, Argument argument);

    Codec<Argument> argumentCodec(EnvironmentAttribute<Subject> attribute);

    LerpFunction<Argument> argumentKeyframeLerp(EnvironmentAttribute<Subject> attribute);

    enum OperationId implements StringRepresentable {

        OVERRIDE,
        ALPHA_BLEND,
        ADD,
        SUBTRACT,
        MULTIPLY,
        BLEND_TO_GRAY,
        MINIMUM,
        MAXIMUM,
        AND,
        NAND,
        OR,
        NOR,
        XOR,
        XNOR;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/world/attribute/modifier/AttributeModifier$OperationId.getSerializedName:()Ljava/lang/String;");
        }
    }

    record OverrideModifier<Value>() implements AttributeModifier<Value, Value> {

        public Value apply(Value subject, Value argument) {
            throw Unimplemented.forMember("net/minecraft/world/attribute/modifier/AttributeModifier$OverrideModifier.apply:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;");
        }

        public Codec<Value> argumentCodec(EnvironmentAttribute<Value> attribute) {
            throw Unimplemented.forMember("net/minecraft/world/attribute/modifier/AttributeModifier$OverrideModifier.argumentCodec:(Lnet/minecraft/world/attribute/EnvironmentAttribute;)Lcom/mojang/serialization/Codec;");
        }

        public LerpFunction<Value> argumentKeyframeLerp(EnvironmentAttribute<Value> attribute) {
            throw Unimplemented.forMember("net/minecraft/world/attribute/modifier/AttributeModifier$OverrideModifier.argumentKeyframeLerp:(Lnet/minecraft/world/attribute/EnvironmentAttribute;)Lnet/minecraft/world/attribute/LerpFunction;");
        }
    }
}
