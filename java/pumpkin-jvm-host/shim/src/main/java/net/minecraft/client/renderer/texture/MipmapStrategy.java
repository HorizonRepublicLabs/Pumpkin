package net.minecraft.client.renderer.texture;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum MipmapStrategy implements StringRepresentable {

    AUTO, MEAN, CUTOUT, STRICT_CUTOUT, DARK_CUTOUT;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/texture/MipmapStrategy.getSerializedName:()Ljava/lang/String;");
    }
}
