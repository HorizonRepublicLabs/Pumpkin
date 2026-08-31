package net.minecraft.world.level.block.state.properties;

import net.minecraft.network.chat.Component;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum TestBlockMode implements StringRepresentable {

    START, LOG, FAIL, ACCEPT;

    // Pumpkin divergence: vanilla body -- the lowercase constant name.
    public String getSerializedName() {
        return name().toLowerCase(java.util.Locale.ROOT);
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/TestBlockMode.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }
}
