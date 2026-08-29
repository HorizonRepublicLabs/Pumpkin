package net.minecraft.world.level.block.state.properties;

import com.mojang.serialization.Codec;
import java.util.List;
import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public abstract class Property<T extends Comparable<T>> {

    protected Property(String name, Class<T> clazz) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.<init>:(Ljava/lang/String;Ljava/lang/Class;)V");
    }

    public Codec<T> codec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.codec:()Lcom/mojang/serialization/Codec;");
    }

    public Codec<Property.Value<T>> valueCodec() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.valueCodec:()Lcom/mojang/serialization/Codec;");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.getName:()Ljava/lang/String;");
    }

    public abstract List<T> getPossibleValues();

    public abstract String getName(final T value);

    public abstract Optional<T> getValue(final String name);

    public abstract int getInternalIndex(final T value);

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.toString:()Ljava/lang/String;");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.equals:(Ljava/lang/Object;)Z");
    }

    public final int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.hashCode:()I");
    }

    public record Value<T extends Comparable<T>>(Property<T> property, T value) {

        public String toString() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property$Value.toString:()Ljava/lang/String;");
        }
    }

    public Property() {
    }
}
