package net.minecraft.server.players;

import com.google.gson.JsonObject;
import net.minecraft.server.permissions.LevelBasedPermissionSet;
import dev.pumpkin.shim.Unimplemented;

public class ServerOpListEntry extends StoredUserEntry<NameAndId> {

    public ServerOpListEntry(NameAndId user, LevelBasedPermissionSet permissions, boolean bypassesPlayerLimit) {
    }

    public ServerOpListEntry(JsonObject object) {
    }

    public LevelBasedPermissionSet permissions() {
        throw Unimplemented.forMember("net/minecraft/server/players/ServerOpListEntry.permissions:()Lnet/minecraft/server/permissions/LevelBasedPermissionSet;");
    }

    protected void serialize(JsonObject object) {
        throw Unimplemented.forMember("net/minecraft/server/players/ServerOpListEntry.serialize:(Lcom/google/gson/JsonObject;)V");
    }

    public ServerOpListEntry() {
    }
}
