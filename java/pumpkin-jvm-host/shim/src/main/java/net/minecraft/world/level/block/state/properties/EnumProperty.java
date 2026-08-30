package net.minecraft.world.level.block.state.properties;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public final class EnumProperty<T extends Enum<T> & StringRepresentable> extends Property<T> {

    private EnumProperty(String name, Class<T> clazz, List<T> values) {
    }

    // Pumpkin divergence: real body -- the enum constants recorded by create().
    public List<T> getPossibleValues() {
        return pumpkinValues;
    }

    // Pumpkin divergence: real body -- looks up by serialized name, as vanilla does.
    @SuppressWarnings("unchecked")
    public Optional<T> getValue(String name) {
        return Optional.ofNullable((T) pumpkinParse.get(name));
    }

    // Pumpkin divergence: real body -- an enum property value's name is its serialized name.
    public String getName(T value) {
        return value.getSerializedName();
    }

    public int getInternalIndex(T value) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.getInternalIndex:(Ljava/lang/Enum;)I");
    }

    // Pumpkin divergence: identity equality. Vanilla compares name and value list, but every
    // property a mod hands us is a static singleton, so identity gives the same answer and
    // needs nothing the shim lacks. StateHolder.setValue's Map.copyOf probes this.
    public boolean equals(Object o) {
        return this == o;
    }

    public int generateHashCode() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.generateHashCode:()I");
    }

    // Pumpkin divergence: real body -- records the enum constants and their serialized
    // names so registration can describe every state this property produces.
    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String name, Class<T> clazz) {
        EnumProperty<T> property = new EnumProperty<>();
        property.pumpkinName = name;
        property.pumpkinValues = List.of(clazz.getEnumConstants());
        for (T value : property.pumpkinValues) {
            property.pumpkinPossibleValues.add(value.getSerializedName());
            property.pumpkinParse.put(value.getSerializedName(), value);
        }
        return property;
    }

    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String name, Class<T> clazz, Predicate<T> filter) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.create:(Ljava/lang/String;Ljava/lang/Class;Ljava/util/function/Predicate;)Lnet/minecraft/world/level/block/state/properties/EnumProperty;");
    }

    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String name, Class<T> clazz, T... values) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.create:(Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Enum;)Lnet/minecraft/world/level/block/state/properties/EnumProperty;");
    }

    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String name, Class<T> clazz, List<T> values) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.create:(Ljava/lang/String;Ljava/lang/Class;Ljava/util/List;)Lnet/minecraft/world/level/block/state/properties/EnumProperty;");
    }

    public EnumProperty() {
    }

    // Pumpkin divergence: the constants create() recorded, typed; pumpkinPossibleValues on
    // Property carries their string forms for registration.
    private List<T> pumpkinValues = List.of();
}
