package net.minecraft.server.dialog;

import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public enum DialogAction implements StringRepresentable {

    CLOSE, NONE, WAIT_FOR_RESPONSE;

    public String getSerializedName() {
        throw Unimplemented.forMember("net/minecraft/server/dialog/DialogAction.getSerializedName:()Ljava/lang/String;");
    }
}
