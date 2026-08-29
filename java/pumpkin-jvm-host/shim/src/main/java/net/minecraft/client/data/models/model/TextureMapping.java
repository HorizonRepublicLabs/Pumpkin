package net.minecraft.client.data.models.model;

import net.minecraft.client.resources.model.sprite.Material;
import dev.pumpkin.shim.Unimplemented;

public class TextureMapping {

    public TextureMapping putForced(TextureSlot slot, Material material) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/TextureMapping.putForced:(Lnet/minecraft/client/data/models/model/TextureSlot;Lnet/minecraft/client/resources/model/sprite/Material;)Lnet/minecraft/client/data/models/model/TextureMapping;");
    }

    public Material get(TextureSlot slot) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/TextureMapping.get:(Lnet/minecraft/client/data/models/model/TextureSlot;)Lnet/minecraft/client/resources/model/sprite/Material;");
    }

    public static TextureMapping singleSlot(TextureSlot slot, Material id) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/TextureMapping.singleSlot:(Lnet/minecraft/client/data/models/model/TextureSlot;Lnet/minecraft/client/resources/model/sprite/Material;)Lnet/minecraft/client/data/models/model/TextureMapping;");
    }

    public TextureMapping copy() {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/TextureMapping.copy:()Lnet/minecraft/client/data/models/model/TextureMapping;");
    }

    protected TextureMapping() {
    }
}
