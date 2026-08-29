package net.minecraft.client.resources;

import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import dev.pumpkin.shim.Unimplemented;

public class MapTextureManager implements AutoCloseable {

    public MapTextureManager(TextureManager textureManager) {
        throw Unimplemented.forMember("net/minecraft/client/resources/MapTextureManager.<init>:(Lnet/minecraft/client/renderer/texture/TextureManager;)V");
    }

    public void close() {
        throw Unimplemented.forMember("net/minecraft/client/resources/MapTextureManager.close:()V");
    }

    private class MapInstance implements AutoCloseable {

        private MapInstance(int id, MapItemSavedData data) {
            throw Unimplemented.forMember("net/minecraft/client/resources/MapTextureManager$MapInstance.<init>:(ILnet/minecraft/world/level/saveddata/maps/MapItemSavedData;)V");
        }

        public void close() {
            throw Unimplemented.forMember("net/minecraft/client/resources/MapTextureManager$MapInstance.close:()V");
        }

        protected MapInstance() {
        }
    }

    public MapTextureManager() {
    }
}
