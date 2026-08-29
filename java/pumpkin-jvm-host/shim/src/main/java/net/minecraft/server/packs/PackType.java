package net.minecraft.server.packs;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum PackType implements StringRepresentable {

    CLIENT_RESOURCES, SERVER_DATA;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/server/packs/PackType.getSerializedName:()Ljava/lang/String;");
    }
}
