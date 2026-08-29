package net.minecraft.world.level.block.state;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import net.minecraft.world.level.block.state.properties.Property;
import dev.pumpkin.shim.Unimplemented;

public class StateDefinition<O, S extends StateHolder<O, S>> {

    protected StateDefinition(Function<O, S> defaultState, O owner, StateDefinition.Factory<O, S> factory, Map<String, Property<?>> properties) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateDefinition.<init>:(Ljava/util/function/Function;Ljava/lang/Object;Lnet/minecraft/world/level/block/state/StateDefinition$Factory;Ljava/util/Map;)V");
    }

    public S any() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateDefinition.any:()Lnet/minecraft/world/level/block/state/StateHolder;");
    }

    public O getOwner() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateDefinition.getOwner:()Ljava/lang/Object;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateDefinition.toString:()Ljava/lang/String;");
    }

    public static class Builder<O, S extends StateHolder<O, S>> {

        public Builder(O owner) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateDefinition$Builder.<init>:(Ljava/lang/Object;)V");
        }

        public StateDefinition.Builder<O, S> add(Property<?>... properties) {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateDefinition$Builder.add:([Lnet/minecraft/world/level/block/state/properties/Property;)Lnet/minecraft/world/level/block/state/StateDefinition$Builder;");
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
