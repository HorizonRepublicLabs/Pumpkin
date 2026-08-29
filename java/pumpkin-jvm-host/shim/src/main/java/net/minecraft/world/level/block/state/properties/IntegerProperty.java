package net.minecraft.world.level.block.state.properties;

import java.util.List;
import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public final class IntegerProperty extends Property<Integer> {

    private IntegerProperty(String name, int min, int max) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/IntegerProperty.<init>:(Ljava/lang/String;II)V");
    }

    public List<Integer> getPossibleValues() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/IntegerProperty.getPossibleValues:()Ljava/util/List;");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/IntegerProperty.equals:(Ljava/lang/Object;)Z");
    }

    public int generateHashCode() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/IntegerProperty.generateHashCode:()I");
    }

    public static IntegerProperty create(String name, int min, int max) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/IntegerProperty.create:(Ljava/lang/String;II)Lnet/minecraft/world/level/block/state/properties/IntegerProperty;");
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

    protected IntegerProperty() {
    }
}
