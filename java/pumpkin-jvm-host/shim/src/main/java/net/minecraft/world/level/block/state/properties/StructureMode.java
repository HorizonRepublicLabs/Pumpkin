package net.minecraft.world.level.block.state.properties;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum StructureMode implements StringRepresentable {

    SAVE, LOAD, CORNER, DATA;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/StructureMode.getSerializedName:()Ljava/lang/String;");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/StructureMode.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }
}
