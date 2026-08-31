package net.neoforged.neoforge.client.extensions;

import net.minecraft.client.resources.model.ResolvableModel;
import net.minecraft.util.context.ContextMap;
import dev.pumpkin.shim.Unimplemented;

public interface UnbakedModelExtension extends ResolvableModel {

    default void fillAdditionalProperties(ContextMap.Builder propertiesBuilder) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/UnbakedModelExtension.fillAdditionalProperties:(Lnet/minecraft/util/context/ContextMap$Builder;)V");
    }

    default void resolveDependencies(Resolver resolver) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/UnbakedModelExtension.resolveDependencies:(Lnet/neoforged/neoforge/client/extensions/Resolver;)V");
    }
}
