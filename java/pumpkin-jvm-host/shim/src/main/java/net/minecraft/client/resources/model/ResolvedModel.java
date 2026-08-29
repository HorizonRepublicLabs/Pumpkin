package net.minecraft.client.resources.model;

import net.neoforged.neoforge.client.extensions.ResolvedModelExtension;

public interface ResolvedModel extends ModelDebugName, ResolvedModelExtension {

    UnbakedModel wrapped();

    ResolvedModel parent();
}
