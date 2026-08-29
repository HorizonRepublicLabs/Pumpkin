package net.neoforged.neoforge.common.extensions;

import dev.pumpkin.shim.Unimplemented;

public interface TooltipFlagExtension {

    default boolean hasControlDown() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/TooltipFlagExtension.hasControlDown:()Z");
    }

    default boolean hasShiftDown() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/TooltipFlagExtension.hasShiftDown:()Z");
    }

    default boolean hasAltDown() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/TooltipFlagExtension.hasAltDown:()Z");
    }
}
