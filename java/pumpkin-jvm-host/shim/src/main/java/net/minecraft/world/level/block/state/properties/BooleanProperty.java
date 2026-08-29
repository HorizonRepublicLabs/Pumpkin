package net.minecraft.world.level.block.state.properties;

import java.util.List;
import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public final class BooleanProperty extends Property<Boolean> {

    private BooleanProperty(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/BooleanProperty.<init>:(Ljava/lang/String;)V");
    }

    public List<Boolean> getPossibleValues() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/BooleanProperty.getPossibleValues:()Ljava/util/List;");
    }

    public static BooleanProperty create(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/BooleanProperty.create:(Ljava/lang/String;)Lnet/minecraft/world/level/block/state/properties/BooleanProperty;");
    }

    public Optional<Boolean> getValue(String name) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/BooleanProperty.getValue:(Ljava/lang/String;)Ljava/util/Optional;");
    }

    public String getName(Boolean value) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/BooleanProperty.getName:(Ljava/lang/Boolean;)Ljava/lang/String;");
    }

    public int getInternalIndex(Boolean value) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/BooleanProperty.getInternalIndex:(Ljava/lang/Boolean;)I");
    }

    public BooleanProperty() {
    }
}
