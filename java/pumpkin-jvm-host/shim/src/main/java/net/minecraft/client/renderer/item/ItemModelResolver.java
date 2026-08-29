package net.minecraft.client.renderer.item;

import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.world.entity.ItemOwner;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import dev.pumpkin.shim.Unimplemented;

public class ItemModelResolver {

    public ItemModelResolver(ModelManager modelManager) {
    }

    public void updateForTopItem(ItemStackRenderState output, ItemStack item, ItemDisplayContext displayContext, Level level, ItemOwner owner, int seed) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ItemModelResolver.updateForTopItem:(Lnet/minecraft/client/renderer/item/ItemStackRenderState;Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/item/ItemDisplayContext;Lnet/minecraft/world/level/Level;Lnet/minecraft/world/entity/ItemOwner;I)V");
    }

    public ItemModelResolver() {
    }
}
