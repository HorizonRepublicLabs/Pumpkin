package net.minecraft.nbt;

import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public interface NumericTag extends PrimitiveTag {

    byte byteValue();

    short shortValue();

    int intValue();

    long longValue();

    float floatValue();

    double doubleValue();

    Number box();

    default Optional<Number> asNumber() {
        throw Unimplemented.forMember("net/minecraft/nbt/NumericTag.asNumber:()Ljava/util/Optional;");
    }

    default Optional<Byte> asByte() {
        throw Unimplemented.forMember("net/minecraft/nbt/NumericTag.asByte:()Ljava/util/Optional;");
    }

    default Optional<Short> asShort() {
        throw Unimplemented.forMember("net/minecraft/nbt/NumericTag.asShort:()Ljava/util/Optional;");
    }

    default Optional<Integer> asInt() {
        throw Unimplemented.forMember("net/minecraft/nbt/NumericTag.asInt:()Ljava/util/Optional;");
    }

    default Optional<Long> asLong() {
        throw Unimplemented.forMember("net/minecraft/nbt/NumericTag.asLong:()Ljava/util/Optional;");
    }

    default Optional<Float> asFloat() {
        throw Unimplemented.forMember("net/minecraft/nbt/NumericTag.asFloat:()Ljava/util/Optional;");
    }

    default Optional<Double> asDouble() {
        throw Unimplemented.forMember("net/minecraft/nbt/NumericTag.asDouble:()Ljava/util/Optional;");
    }

    default Optional<Boolean> asBoolean() {
        throw Unimplemented.forMember("net/minecraft/nbt/NumericTag.asBoolean:()Ljava/util/Optional;");
    }
}
