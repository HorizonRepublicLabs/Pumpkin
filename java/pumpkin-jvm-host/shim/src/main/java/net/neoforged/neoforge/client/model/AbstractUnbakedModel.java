package net.neoforged.neoforge.client.model;

import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.client.resources.model.cuboid.ItemTransforms;
import net.minecraft.client.resources.model.sprite.TextureSlots;
import net.minecraft.resources.Identifier;
import net.minecraft.util.context.ContextMap;
import dev.pumpkin.shim.Unimplemented;

public abstract class AbstractUnbakedModel implements UnbakedModel {

    protected AbstractUnbakedModel(StandardModelParameters parameters) {
    }

    public Boolean ambientOcclusion() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/AbstractUnbakedModel.ambientOcclusion:()Ljava/lang/Boolean;");
    }

    public GuiLight guiLight() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/AbstractUnbakedModel.guiLight:()Lnet/neoforged/neoforge/client/model/GuiLight;");
    }

    public ItemTransforms transforms() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/AbstractUnbakedModel.transforms:()Lnet/minecraft/client/resources/model/cuboid/ItemTransforms;");
    }

    public TextureSlots.Data textureSlots() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/AbstractUnbakedModel.textureSlots:()Lnet/minecraft/client/resources/model/sprite/TextureSlots$Data;");
    }

    public Identifier parent() {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/AbstractUnbakedModel.parent:()Lnet/minecraft/resources/Identifier;");
    }

    public void fillAdditionalProperties(ContextMap.Builder propertiesBuilder) {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/AbstractUnbakedModel.fillAdditionalProperties:(Lnet/minecraft/util/context/ContextMap$Builder;)V");
    }

    public AbstractUnbakedModel() {
    }
}
