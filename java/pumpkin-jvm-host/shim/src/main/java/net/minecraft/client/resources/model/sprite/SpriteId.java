package net.minecraft.client.resources.model.sprite;

import java.util.function.Function;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.resources.Identifier;
import dev.pumpkin.shim.Unimplemented;

public record SpriteId(Identifier atlasLocation, Identifier texture) {

    public RenderType renderType(Function<Identifier, RenderType> renderType) {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/sprite/SpriteId.renderType:(Ljava/util/function/Function;)Lnet/minecraft/client/renderer/rendertype/RenderType;");
    }
}
