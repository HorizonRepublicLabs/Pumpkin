package net.minecraft.client.data.models.blockstates;

import java.util.List;
import net.minecraft.world.level.block.state.properties.Property;
import dev.pumpkin.shim.Unimplemented;

public record PropertyValueList(List<Property.Value<?>> values) {

    public String getKey() {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyValueList.getKey:()Ljava/lang/String;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/data/models/blockstates/PropertyValueList.toString:()Ljava/lang/String;");
    }
}
