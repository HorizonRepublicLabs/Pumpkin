package net.neoforged.neoforge.common.extensions;

import dev.pumpkin.shim.Unimplemented;

public interface IHolderSetExtension<T> {

    default boolean isImmediatelyResolvable() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IHolderSetExtension.isImmediatelyResolvable:()Z");
    }

    public static enum SerializationType {

        UNKNOWN, STRING, LIST, OBJECT
    }
}
