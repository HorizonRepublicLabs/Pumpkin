package net.minecraft.client.resources.model.sprite;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelDebugName;
import dev.pumpkin.shim.Unimplemented;

public abstract class MaterialBaker {

    public MaterialBaker(TextureAtlasSprite missingSprite) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/MaterialBaker.<init>:(Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;)V");
    }

    public Material.Baked get(Material material, ModelDebugName name) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/MaterialBaker.get:(Lnet/minecraft/client/resources/model/sprite/Material;Lnet/minecraft/client/resources/model/ModelDebugName;)Lnet/minecraft/client/resources/model/sprite/Material$Baked;");
    }

    protected abstract Material.Baked bake(Material material);

    public MaterialBaker() {
    }
}
