package net.minecraft.world.level.block.state.properties;

import java.util.List;
import java.util.Optional;
import dev.pumpkin.shim.Unimplemented;

public final class BooleanProperty extends Property<Boolean> {

    private BooleanProperty(String name) {
    }

    public List<Boolean> getPossibleValues() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/BooleanProperty.getPossibleValues:()Ljava/util/List;");
    }

    // Pumpkin divergence: real body -- a named property is just its name here.
    public static BooleanProperty create(String name) {
        BooleanProperty property = new BooleanProperty();
        property.pumpkinName = name;
        return property;
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
