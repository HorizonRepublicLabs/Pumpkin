package net.neoforged.neoforge.common.extensions;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import dev.pumpkin.shim.Unimplemented;

public interface ValueOutputExtension {

    default void store(CompoundTag tag) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ValueOutputExtension.store:(Lnet/minecraft/nbt/CompoundTag;)V");
    }

    default void putChild(String key, ValueIOSerializable child) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ValueOutputExtension.putChild:(Ljava/lang/String;Lnet/neoforged/neoforge/common/util/ValueIOSerializable;)V");
    }
}
