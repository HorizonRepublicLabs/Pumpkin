package net.minecraft.client.renderer.item;

import net.minecraft.util.RegistryContextSwapper;

public record ClientItem(ItemModel.Unbaked model, ClientItem.Properties properties, RegistryContextSwapper registrySwapper) {

    public ClientItem(ItemModel.Unbaked model, ClientItem.Properties properties) {
        this((ItemModel.Unbaked) null, (ClientItem.Properties) null, (RegistryContextSwapper) null);
    }

    public record Properties(boolean handAnimationOnSwap, boolean oversizedInGui, float swapAnimationScale) {

        public static final ClientItem.Properties DEFAULT = null;
    }
}
