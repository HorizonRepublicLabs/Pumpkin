package net.minecraft.world.level.block.state.properties;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public abstract class Property<T extends Comparable<T>> {

    protected Property(String name, Class<T> clazz) {
    }

    public Codec<T> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.codec:()Lcom/mojang/serialization/Codec;");
    }

    public Codec<Property.Value<T>> valueCodec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.valueCodec:()Lcom/mojang/serialization/Codec;");
    }

    // Pumpkin divergence: real body, backed by the name create() recorded.
    public String pumpkinName;

    public String getName() {
        if (pumpkinName == null) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.getName:()Ljava/lang/String;");
        }
        return pumpkinName;
    }

    public abstract List<T> getPossibleValues();

    public abstract String getName(final T value);

    public abstract Optional<T> getValue(final String name);

    public abstract int getInternalIndex(final T value);

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.toString:()Ljava/lang/String;");
    }

    // Pumpkin divergence: real bodies. Properties are singletons -- create() is the only
    // maker -- so identity semantics are correct, and HashMap needs both of these the
    // moment a property becomes a map key, which the state machinery makes routine.
    public boolean equals(Object o) {
        return this == o;
    }

    public final int hashCode() {
        return System.identityHashCode(this);
    }

    public record Value<T extends Comparable<T>>(Property<T> property, T value) {

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property$Value.toString:()Ljava/lang/String;");
        }
    }

    public Property() {
    }
}
