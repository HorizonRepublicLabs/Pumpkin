package net.neoforged.neoforge.client.extensions;

import net.minecraft.util.context.ContextMap;
import dev.pumpkin.shim.Unimplemented;

public interface ResolvedModelExtension {

    default ContextMap getTopAdditionalProperties() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/ResolvedModelExtension.getTopAdditionalProperties:()Lnet/minecraft/util/context/ContextMap;");
    }
}
