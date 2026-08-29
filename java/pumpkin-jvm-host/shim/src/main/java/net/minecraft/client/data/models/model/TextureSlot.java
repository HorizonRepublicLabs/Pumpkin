package net.minecraft.client.data.models.model;

import dev.pumpkin.shim.Unimplemented;

public final class TextureSlot {

    public static final TextureSlot LAYER0 = null;

    public static final TextureSlot LAYER1 = null;

    public static TextureSlot create(String id) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/TextureSlot.create:(Ljava/lang/String;)Lnet/minecraft/client/data/models/model/TextureSlot;");
    }

    public static TextureSlot create(String id, TextureSlot parent) {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/TextureSlot.create:(Ljava/lang/String;Lnet/minecraft/client/data/models/model/TextureSlot;)Lnet/minecraft/client/data/models/model/TextureSlot;");
    }

    private TextureSlot(String id, TextureSlot parent) {
    }

    public String getId() {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/TextureSlot.getId:()Ljava/lang/String;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/data/models/model/TextureSlot.toString:()Ljava/lang/String;");
    }

    public TextureSlot() {
    }
}
