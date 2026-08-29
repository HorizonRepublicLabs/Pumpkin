package net.minecraft.client.resources.model.sprite;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record Material(Identifier sprite, boolean forceTranslucent) {

    public Material(Identifier sprite) {
        this((Identifier) null, (boolean) false);
        throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/Material.<init>:(Lnet/minecraft/resources/Identifier;)V");
    }

    public record Baked(TextureAtlasSprite sprite, boolean forceTranslucent) {
    }
}
