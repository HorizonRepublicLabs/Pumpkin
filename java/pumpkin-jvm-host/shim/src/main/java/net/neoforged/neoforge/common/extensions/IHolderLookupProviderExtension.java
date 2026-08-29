package net.neoforged.neoforge.common.extensions;

import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import dev.pumpkin.shim.Unimplemented;

public interface IHolderLookupProviderExtension {

    default <T> Holder<T> holderOrThrow(ResourceKey<T> key) {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IHolderLookupProviderExtension.holderOrThrow:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/core/Holder;");
    }
}
