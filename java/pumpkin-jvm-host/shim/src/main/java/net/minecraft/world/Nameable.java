package net.minecraft.world;

import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public interface Nameable {

    Component getName();

    default Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/world/Nameable.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }
}
