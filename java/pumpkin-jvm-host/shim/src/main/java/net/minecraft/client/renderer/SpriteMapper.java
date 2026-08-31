package net.minecraft.client.renderer;

import net.minecraft.client.resources.model.sprite.SpriteId;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record SpriteMapper(Identifier sheet, String prefix) {

    public SpriteId apply(Identifier path) {
        throw Unimplemented.forMember("net/minecraft/client/renderer/SpriteMapper.apply:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/resources/model/sprite/SpriteId;");
    }
}
