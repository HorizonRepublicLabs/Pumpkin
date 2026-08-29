package net.neoforged.neoforge.client.extensions;

import net.minecraft.client.resources.model.ResolvableModel;
import dev.pumpkin.shim.Unimplemented;

public interface UnbakedModelExtension extends ResolvableModel {

    default void resolveDependencies(Resolver resolver) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/UnbakedModelExtension.resolveDependencies:(Lnet/neoforged/neoforge/client/extensions/Resolver;)V");
    }
}
