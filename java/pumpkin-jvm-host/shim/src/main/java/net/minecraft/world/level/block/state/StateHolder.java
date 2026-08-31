package net.minecraft.world.level.block.state;

import java.util.Optional;
import java.util.stream.Stream;
import net.minecraft.world.level.block.state.properties.Property;
import dev.pumpkin.shim.Unimplemented;

public abstract class StateHolder<O, S> {

    protected StateHolder(O owner, Property<?>[] propertyKeys, Comparable<?>[] propertyValues) {
    }

    public <T extends Comparable<T>> S cycle(Property<T> property) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateHolder.cycle:(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/lang/Object;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateHolder.toString:()Ljava/lang/String;");
    }

    public final boolean equals(Object obj) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateHolder.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateHolder.hashCode:()I");
    }

    public boolean hasProperty(Property<?> property) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateHolder.hasProperty:(Lnet/minecraft/world/level/block/state/properties/Property;)Z");
    }

    // Pumpkin divergence: real bodies over a copy-on-write property map. Enough for
    // registration and the mods' own reads; NOT interned, so vanilla's states-are-identity
    // guarantee does not hold yet -- that arrives with the Rust state binding. A property
    // never set fails loudly with the property's name, not a null.
    public java.util.Map<Property<?>, Comparable<?>> pumpkinValues = java.util.Map.of();

    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>> T getValue(Property<T> property) {
        Comparable<?> value = pumpkinValues.get(property);
        if (value == null) {
            throw new IllegalArgumentException("property " + property + " was never set on " + this);
        }
        return (T) value;
    }

    public <T extends Comparable<T>> Optional<T> getOptionalValue(Property<T> property) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateHolder.getOptionalValue:(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/util/Optional;");
    }

    // Pumpkin divergence: real body. Returns a sibling state with one value changed --
    // copy-on-write, not interned; see getValue's comment.
    @SuppressWarnings("unchecked")
    public <T extends Comparable<T>, V extends T> S setValue(Property<T> property, V value) {
        StateHolder<O, S> next = pumpkinSibling();
        java.util.Map<Property<?>, Comparable<?>> map = new java.util.HashMap<>(pumpkinValues);
        map.put(property, value);
        next.pumpkinValues = java.util.Map.copyOf(map);
        return (S) next;
    }

    // Pumpkin divergence: how setValue makes the copy. Subclasses that carry more state
    // override to preserve it; BlockState keeps its owning block this way.
    protected StateHolder<O, S> pumpkinSibling() {
        throw new UnsupportedOperationException(getClass().getName() + " cannot copy itself");
    }

    public Stream<Property.Value<?>> getValues() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateHolder.getValues:()Ljava/util/stream/Stream;");
    }

    public StateHolder() {
    }
}
