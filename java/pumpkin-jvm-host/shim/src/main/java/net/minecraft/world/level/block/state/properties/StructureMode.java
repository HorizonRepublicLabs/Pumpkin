package net.minecraft.world.level.block.state.properties;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum StructureMode implements StringRepresentable {

    SAVE, LOAD, CORNER, DATA;

    // Pumpkin divergence: vanilla body -- the lowercase constant name.
    public String getSerializedName() {
        return name().toLowerCase(java.util.Locale.ROOT);
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/StructureMode.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }
}
