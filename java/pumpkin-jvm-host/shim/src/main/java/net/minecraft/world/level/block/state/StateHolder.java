package net.minecraft.world.level.block.state;

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

    public <T extends Comparable<T>> T getValue(Property<T> property) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateHolder.getValue:(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/lang/Comparable;");
    }

    public <T extends Comparable<T>, V extends T> S setValue(Property<T> property, V value) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateHolder.setValue:(Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/T;)Ljava/lang/Object;");
    }

    public StateHolder() {
    }
}
