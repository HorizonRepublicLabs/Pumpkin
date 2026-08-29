package net.minecraft.client;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum PreferredGraphicsApi implements StringRepresentable {

    DEFAULT, OPENGL, VULKAN;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/client/PreferredGraphicsApi.getSerializedName:()Ljava/lang/String;");
    }
}
