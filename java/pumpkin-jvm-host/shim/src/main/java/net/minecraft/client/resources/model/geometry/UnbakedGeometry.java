package net.minecraft.client.resources.model.geometry;

import net.minecraft.client.renderer.block.dispatch.ModelState;
import net.minecraft.client.resources.model.ModelBaker;
import net.minecraft.client.resources.model.ModelDebugName;
import net.minecraft.client.resources.model.sprite.TextureSlots;
import net.neoforged.neoforge.client.extensions.UnbakedGeometryExtension;

public interface UnbakedGeometry extends UnbakedGeometryExtension {

    QuadCollection bake(TextureSlots textureSlots, ModelBaker modelBaker, ModelState modelState, ModelDebugName name);
}
