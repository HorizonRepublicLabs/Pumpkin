package net.neoforged.neoforge.common.extensions;

import dev.pumpkin.shim.Unimplemented;

public interface IBlockStateExtension {

    default boolean isEmpty() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IBlockStateExtension.isEmpty:()Z");
    }
}
