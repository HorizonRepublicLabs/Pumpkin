package net.minecraft.client.renderer.item;

import java.util.List;
import java.util.function.Supplier;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.model.geometry.BakedQuad;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.joml.Matrix4fc;
import org.joml.Vector3fc;
import dev.pumpkin.shim.Unimplemented;

public class MissingItemModel implements ItemModel {

    public MissingItemModel(List<BakedQuad> quads, ModelRenderProperties properties) {
    }

    private MissingItemModel(List<BakedQuad> quads, Supplier<Vector3fc[]> extents, ModelRenderProperties properties, Matrix4fc transform) {
    }

    public void update(ItemStackRenderState output, ItemStack item, ItemModelResolver resolver, ItemDisplayContext displayContext, ClientLevel level, ItemOwner owner, int seed) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/MissingItemModel.update:(Lnet/minecraft/client/renderer/item/ItemStackRenderState;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/item/ItemModelResolver;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/client/multiplayer/ClientLevel;Lnet/minecraft/world/entity/ItemOwner;I)V");
    }

    public MissingItemModel() {
    }
}
