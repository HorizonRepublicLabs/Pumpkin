package net.minecraft.world;

import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public interface Nameable {

    Component getName();

    // Pumpkin divergence: the vanilla default bodies -- no custom name unless a
    // subclass carries one.
    default String getPlainTextName() {
        return getName().getString();
    }

    default boolean hasCustomName() {
        return getCustomName() != null;
    }

    default Component getDisplayName() {
        return getName();
    }

    default Component getCustomName() {
        return null;
    }
}
