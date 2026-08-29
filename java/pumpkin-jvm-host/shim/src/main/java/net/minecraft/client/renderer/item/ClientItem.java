package net.minecraft.client.renderer.item;

import net.minecraft.util.RegistryContextSwapper;
import dev.pumpkin.shim.Unimplemented;

public record ClientItem(ItemModel.Unbaked model, ClientItem.Properties properties, RegistryContextSwapper registrySwapper) {

    public ClientItem(ItemModel.Unbaked model, ClientItem.Properties properties) {
        this((ItemModel.Unbaked) null, (ClientItem.Properties) null, (RegistryContextSwapper) null);
        throw Unimplemented.forMember("net/minecraft/client/renderer/item/ClientItem.<init>:(Lnet/minecraft/client/renderer/item/ItemModel$Unbaked;Lnet/minecraft/client/renderer/item/ClientItem$Properties;)V");
    }

    public record Properties(boolean handAnimationOnSwap, boolean oversizedInGui, float swapAnimationScale) {

        public static final ClientItem.Properties DEFAULT = null;
    }
}
