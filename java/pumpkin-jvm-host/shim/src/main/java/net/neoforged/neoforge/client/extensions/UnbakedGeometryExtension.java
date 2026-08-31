package net.neoforged.neoforge.client.extensions;

import net.minecraft.client.renderer.block.dispatch.ModelState;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelDebugName;
import net.minecraft.client.resources.model.geometry.QuadCollection;
import net.minecraft.client.resources.model.sprite.TextureSlots;
import net.minecraft.util.context.ContextMap;
import dev.pumpkin.shim.Unimplemented;

public interface UnbakedGeometryExtension {

    default QuadCollection bake(TextureSlots textureSlots, ModelBaker baker, ModelState state, ModelDebugName debugName, ContextMap additionalProperties) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/extensions/UnbakedGeometryExtension.bake:(Lnet/minecraft/client/resources/model/sprite/TextureSlots;Lnet/minecraft/client/resources/model/ModelBaker;Lnet/minecraft/client/renderer/block/dispatch/ModelState;Lnet/minecraft/client/resources/model/ModelDebugName;Lnet/minecraft/util/context/ContextMap;)Lnet/minecraft/client/resources/model/geometry/QuadCollection;");
    }
}
