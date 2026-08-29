package net.minecraft.client.renderer.item;

import net.minecraft.client.resources.model.cuboid.ItemTransforms;
import net.minecraft.client.resources.model.sprite.Material;

public record ModelRenderProperties(boolean usesBlockLight, Material.Baked particleMaterial, ItemTransforms transforms) {
}
