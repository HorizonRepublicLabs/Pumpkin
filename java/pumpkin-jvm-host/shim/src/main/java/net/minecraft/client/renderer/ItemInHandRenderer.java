package net.minecraft.client.renderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.item.ItemModelResolver;
import dev.pumpkin.shim.Unimplemented;

public class ItemInHandRenderer {

    public ItemInHandRenderer(Minecraft minecraft, EntityRenderDispatcher entityRenderDispatcher, ItemModelResolver itemModelResolver) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/ItemInHandRenderer.<init>:(Lnet/minecraft/client/Minecraft;Lnet/minecraft/client/renderer/entity/EntityRenderDispatcher;Lnet/minecraft/client/renderer/item/ItemModelResolver;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/ItemInHandRenderer.tick:()V");
    }

    enum HandRenderSelection {

        RENDER_BOTH_HANDS, RENDER_MAIN_HAND_ONLY, RENDER_OFF_HAND_ONLY
    }

    public ItemInHandRenderer() {
    }
}
