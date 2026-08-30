package net.minecraft.world.level.block.state.properties;

import java.util.List;
import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public final class IntegerProperty extends Property<Integer> {

    private IntegerProperty(String name, int min, int max) {
    }

    public List<Integer> getPossibleValues() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/IntegerProperty.getPossibleValues:()Ljava/util/List;");
    }

    // Pumpkin divergence: identity equality, as with EnumProperty -- every property a mod
    // hands us is a static singleton, and StateHolder.setValue's Map.copyOf probes this.
    public boolean equals(Object o) {
        return this == o;
    }

    public int generateHashCode() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/IntegerProperty.generateHashCode:()I");
    }
    // Pumpkin divergence: real body, values included -- registration walks them.
    public static IntegerProperty create(String name, int min, int max) {
        IntegerProperty property = new IntegerProperty();
        property.pumpkinName = name;
        java.util.List<String> values = new java.util.ArrayList<>();
        java.util.Map<String, Comparable<?>> parse = new java.util.HashMap<>();
        for (int value = min; value <= max; value++) {
            values.add(Integer.toString(value));
            parse.put(Integer.toString(value), value);
        }
        property.pumpkinPossibleValues = java.util.List.copyOf(values);
        property.pumpkinParse = java.util.Map.copyOf(parse);
        return property;
    }

    public Optional<Integer> getValue(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/IntegerProperty.getValue:(Ljava/lang/String;)Ljava/util/Optional;");
    }

    public String getName(Integer value) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/IntegerProperty.getName:(Ljava/lang/Integer;)Ljava/lang/String;");
    }

    public int getInternalIndex(Integer value) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/IntegerProperty.getInternalIndex:(Ljava/lang/Integer;)I");
    }

    public IntegerProperty() {
    }
}
