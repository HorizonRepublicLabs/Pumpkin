package net.minecraft.client.resources.model;

import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.extensions.UnbakedModelExtension;
import dev.pumpkin.shim.Unimplemented;

public interface UnbakedModel extends UnbakedModelExtension {

    default Identifier parent() {
        throw Unimplemented.forMember("net/minecraft/client/resources/model/UnbakedModel.parent:()Lnet/minecraft/resources/Identifier;");
    }

    enum GuiLight {

        FRONT, SIDE;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/client/resources/model/UnbakedModel$GuiLight.getSerializedName:()Ljava/lang/String;");
        }
    }
}
