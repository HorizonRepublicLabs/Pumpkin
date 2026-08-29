package net.minecraft.server.permissions;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum PermissionLevel implements StringRepresentable {

    ALL, MODERATORS, GAMEMASTERS, ADMINS, OWNERS;

    public static PermissionLevel byId(int level) {
        throw Unimplemented.forMember("net/minecraft/server/permissions/PermissionLevel.byId:(I)Lnet/minecraft/server/permissions/PermissionLevel;");
    }

    public int id() {
        throw Unimplemented.forMember("net/minecraft/server/permissions/PermissionLevel.id:()I");
    }

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/server/permissions/PermissionLevel.getSerializedName:()Ljava/lang/String;");
    }
}
