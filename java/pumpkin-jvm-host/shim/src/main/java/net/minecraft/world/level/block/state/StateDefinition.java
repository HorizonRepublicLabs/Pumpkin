package net.minecraft.world.level.block.state;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.world.level.block.state.properties.Property;
import dev.pumpkin.shim.Unimplemented;

public class StateDefinition<O, S extends StateHolder<O, S>> {

    protected StateDefinition(Function<O, S> defaultState, O owner, StateDefinition.Factory<O, S> factory, Map<String, Property<?>> properties) {
    }

    // Pumpkin divergence: real bodies. A definition knows its owner and answers the
    // owner's default state; the property list machines declare is implicit in what
    // setValue records rather than tracked here.
    public java.util.function.Supplier<S> pumpkinAny;

    public S any() {
        if (pumpkinAny == null) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateDefinition.any:()Lnet/minecraft/world/level/block/state/StateHolder;");
        }
        return pumpkinAny.get();
    }

    public O getOwner() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateDefinition.getOwner:()Ljava/lang/Object;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateDefinition.toString:()Ljava/lang/String;");
    }

    public static class Builder<O, S extends StateHolder<O, S>> {

        // Pumpkin divergence: the builder records what add() declares; registration
        // reads it back.
        private final java.util.List<Property<?>> pumpkinProperties = new java.util.ArrayList<>();

        public java.util.List<Property<?>> pumpkinProperties() {
            return pumpkinProperties;
        }

        public Builder(O owner) {
        }

        public StateDefinition.Builder<O, S> add(Property<?>... properties) {
            java.util.Collections.addAll(pumpkinProperties, properties);
            return this;
        }

        public StateDefinition<O, S> create(Function<O, S> defaultState, StateDefinition.Factory<O, S> factory) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateDefinition$Builder.create:(Ljava/util/function/Function;Lnet/minecraft/world/level/block/state/StateDefinition$Factory;)Lnet/minecraft/world/level/block/state/StateDefinition;");
        }

        public Builder() {
        }
    }

    public interface Factory<O, S> {

        S create(O type, Property<?>[] propertyKeys, Comparable<?>[] propertyValues);
    }

    record StateCollection<S extends StateHolder<?, ?>>(Map<List<Comparable<?>>, S> statesByValues, Map<List<Comparable<?>>, S[]> statesByPivotCache) {

        private enum Wildcard {

            INSTANCE
        }
    }

    public StateDefinition() {
    }
}
