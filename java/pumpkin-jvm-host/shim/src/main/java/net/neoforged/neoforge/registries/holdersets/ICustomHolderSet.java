package net.neoforged.neoforge.registries.holdersets;

import net.minecraft.core.HolderSet;
import dev.pumpkin.shim.Unimplemented;

public interface ICustomHolderSet<T> extends HolderSet<T> {

    HolderSetType type();

    default SerializationType serializationType() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/holdersets/ICustomHolderSet.serializationType:()Lnet/neoforged/neoforge/registries/holdersets/SerializationType;");
    }
}
