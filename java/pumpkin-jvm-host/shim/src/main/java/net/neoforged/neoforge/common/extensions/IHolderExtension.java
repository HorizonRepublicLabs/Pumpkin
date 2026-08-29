package net.neoforged.neoforge.common.extensions;

import net.minecraft.resources.ResourceKey;
import net.neoforged.neoforge.registries.datamaps.IWithData;
import dev.pumpkin.shim.Unimplemented;

public interface IHolderExtension<T> extends IWithData<T> {

    default ResourceKey<T> getKey() {
        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IHolderExtension.getKey:()Lnet/minecraft/resources/ResourceKey;");
    }
}
