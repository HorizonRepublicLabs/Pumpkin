package net.minecraft.world.level.block.state.properties;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public final class EnumProperty<T extends Enum<T> & StringRepresentable> extends Property<T> {

    private EnumProperty(String name, Class<T> clazz, List<T> values) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.<init>:(Ljava/lang/String;Ljava/lang/Class;Ljava/util/List;)V");
    }

    public List<T> getPossibleValues() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.getPossibleValues:()Ljava/util/List;");
    }

    public Optional<T> getValue(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.getValue:(Ljava/lang/String;)Ljava/util/Optional;");
    }

    public String getName(T value) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.getName:(Ljava/lang/Enum;)Ljava/lang/String;");
    }

    public int getInternalIndex(T value) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.getInternalIndex:(Ljava/lang/Enum;)I");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.equals:(Ljava/lang/Object;)Z");
    }

    public int generateHashCode() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.generateHashCode:()I");
    }

    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String name, Class<T> clazz) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.create:(Ljava/lang/String;Ljava/lang/Class;)Lnet/minecraft/world/level/block/state/properties/EnumProperty;");
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
}
