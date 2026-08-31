package net.minecraft.client.resources.model;

import net.minecraft.client.resources.model.geometry.UnbakedGeometry;
import net.minecraft.client.resources.model.sprite.TextureSlots;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.extensions.UnbakedModelExtension;
import dev.pumpkin.shim.Unimplemented;

public interface UnbakedModel extends UnbakedModelExtension {

    default TextureSlots.Data textureSlots() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/UnbakedModel.textureSlots:()Lnet/minecraft/client/resources/model/sprite/TextureSlots$Data;");
    }

    default UnbakedGeometry geometry() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/UnbakedModel.geometry:()Lnet/minecraft/client/resources/model/geometry/UnbakedGeometry;");
    }

    default Identifier parent() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/UnbakedModel.parent:()Lnet/minecraft/resources/Identifier;");
    }

    enum GuiLight {

        FRONT, SIDE;

        public boolean lightLikeBlock() {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/UnbakedModel$GuiLight.lightLikeBlock:()Z");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/UnbakedModel$GuiLight.getSerializedName:()Ljava/lang/String;");
        }
    }
}
