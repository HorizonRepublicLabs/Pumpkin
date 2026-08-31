package net.minecraft.client.data.models.blockstates;

import java.util.List;
import java.util.function.Function;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.world.level.block.state.properties.Property;
import dev.pumpkin.shim.Unimplemented;

public abstract class PropertyDispatch<V> {

    abstract List<Property<?>> getDefinedProperties();

    public static <T1 extends Comparable<T1>> PropertyDispatch.C1<MultiVariant, T1> initial(Property<T1> property1) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyDispatch.initial:(Lnet/minecraft/world/level/block/state/properties/Property;)Lnet/minecraft/client/data/models/blockstates/PropertyDispatch$C1;");
    }

    public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> PropertyDispatch.C2<MultiVariant, T1, T2> initial(Property<T1> property1, Property<T2> property2) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyDispatch.initial:(Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/properties/Property;)Lnet/minecraft/client/data/models/blockstates/PropertyDispatch$C2;");
    }

    public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> PropertyDispatch.C3<MultiVariant, T1, T2, T3> initial(Property<T1> property1, Property<T2> property2, Property<T3> property3) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyDispatch.initial:(Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/properties/Property;)Lnet/minecraft/client/data/models/blockstates/PropertyDispatch$C3;");
    }

    public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 extends Comparable<T4>> PropertyDispatch.C4<MultiVariant, T1, T2, T3, T4> initial(Property<T1> property1, Property<T2> property2, Property<T3> property3, Property<T4> property4) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyDispatch.initial:(Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/properties/Property;)Lnet/minecraft/client/data/models/blockstates/PropertyDispatch$C4;");
    }

    public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 extends Comparable<T4>, T5 extends Comparable<T5>> PropertyDispatch.C5<MultiVariant, T1, T2, T3, T4, T5> initial(Property<T1> property1, Property<T2> property2, Property<T3> property3, Property<T4> property4, Property<T5> property5) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyDispatch.initial:(Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/properties/Property;)Lnet/minecraft/client/data/models/blockstates/PropertyDispatch$C5;");
    }

    public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>> PropertyDispatch.C2<VariantMutator, T1, T2> modify(Property<T1> property1, Property<T2> property2) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyDispatch.modify:(Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/properties/Property;)Lnet/minecraft/client/data/models/blockstates/PropertyDispatch$C2;");
    }

    public static <T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> PropertyDispatch.C3<VariantMutator, T1, T2, T3> modify(Property<T1> property1, Property<T2> property2, Property<T3> property3) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyDispatch.modify:(Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/properties/Property;)Lnet/minecraft/client/data/models/blockstates/PropertyDispatch$C3;");
    }

    public static class C1<V, T1 extends Comparable<T1>> extends PropertyDispatch<V> {

        private C1(Property<T1> property1) {
        }

        public List<Property<?>> getDefinedProperties() {
            throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyDispatch$C1.getDefinedProperties:()Ljava/util/List;");
        }

        public PropertyDispatch.C1<V, T1> select(T1 value1, V variants) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyDispatch$C1.select:(Ljava/lang/Comparable;Ljava/lang/Object;)Lnet/minecraft/client/data/models/blockstates/PropertyDispatch$C1;");
        }

        public PropertyDispatch<V> generate(Function<T1, V> generator) {
            throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyDispatch$C1.generate:(Ljava/util/function/Function;)Lnet/minecraft/client/data/models/blockstates/PropertyDispatch;");
        }

        public C1() {
        }
    }

    public static class C2<V, T1 extends Comparable<T1>, T2 extends Comparable<T2>> extends PropertyDispatch<V> {

        private C2(Property<T1> property1, Property<T2> property2) {
        }

        public List<Property<?>> getDefinedProperties() {
            throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyDispatch$C2.getDefinedProperties:()Ljava/util/List;");
        }

        public C2() {
        }
    }

    public static class C3<V, T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>> extends PropertyDispatch<V> {

        private C3(Property<T1> property1, Property<T2> property2, Property<T3> property3) {
        }

        public List<Property<?>> getDefinedProperties() {
            throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyDispatch$C3.getDefinedProperties:()Ljava/util/List;");
        }

        public C3() {
        }
    }

    public static class C4<V, T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 extends Comparable<T4>> extends PropertyDispatch<V> {

        private C4(Property<T1> property1, Property<T2> property2, Property<T3> property3, Property<T4> property4) {
        }

        public List<Property<?>> getDefinedProperties() {
            throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyDispatch$C4.getDefinedProperties:()Ljava/util/List;");
        }

        public C4() {
        }
    }

    public static class C5<V, T1 extends Comparable<T1>, T2 extends Comparable<T2>, T3 extends Comparable<T3>, T4 extends Comparable<T4>, T5 extends Comparable<T5>> extends PropertyDispatch<V> {

        private C5(Property<T1> property1, Property<T2> property2, Property<T3> property3, Property<T4> property4, Property<T5> property5) {
        }

        public List<Property<?>> getDefinedProperties() {
            throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyDispatch$C5.getDefinedProperties:()Ljava/util/List;");
        }

        public C5() {
        }
    }

    public PropertyDispatch() {
    }
}
