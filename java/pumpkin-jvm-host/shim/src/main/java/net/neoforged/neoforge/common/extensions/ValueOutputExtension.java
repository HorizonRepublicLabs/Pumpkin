package net.neoforged.neoforge.common.extensions;

import net.minecraft.nbt.CompoundTag;
import net.neoforged.neoforge.common.util.ValueIOSerializable;
import dev.pumpkin.shim.Unimplemented;

public interface ValueOutputExtension {

    default void store(CompoundTag tag) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ValueOutputExtension.store:(Lnet/minecraft/nbt/CompoundTag;)V");
    }

    // Pumpkin divergence: NeoForge body verbatim -- pure delegation. The extension is
    // mixed into ValueOutput, which owns child().
    default void putChild(String key, ValueIOSerializable child) {
        child.serialize(((net.minecraft.world.level.storage.ValueOutput) this).child(key));
    }
}
