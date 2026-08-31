package net.minecraft.client.resources.model.sprite;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.ModelDebugName;
import dev.pumpkin.shim.Unimplemented;

public abstract class MaterialBaker {

    private final Material.Baked missingSprite = null;

    public MaterialBaker(TextureAtlasSprite missingSprite) {
    }

    public Material.Baked replacementForMissingMaterial(Material material) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/MaterialBaker.replacementForMissingMaterial:(Lnet/minecraft/client/resources/model/sprite/Material;)Lnet/minecraft/client/resources/model/sprite/Material$Baked;");
    }

    public Material.Baked get(Material material, ModelDebugName name) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/MaterialBaker.get:(Lnet/minecraft/client/resources/model/sprite/Material;Lnet/minecraft/client/resources/model/ModelDebugName;)Lnet/minecraft/client/resources/model/sprite/Material$Baked;");
    }

    protected abstract Material.Baked bake(Material material);

    public Material.Baked resolveSlot(TextureSlots slots, String id, ModelDebugName name) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/MaterialBaker.resolveSlot:(Lnet/minecraft/client/resources/model/sprite/TextureSlots;Ljava/lang/String;Lnet/minecraft/client/resources/model/ModelDebugName;)Lnet/minecraft/client/resources/model/sprite/Material$Baked;");
    }

    public MaterialBaker() {
    }
}
