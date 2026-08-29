package net.minecraft.server.players;

import com.google.gson.JsonObject;
import java.util.Date;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public class UserBanListEntry extends BanListEntry<NameAndId> {

    public UserBanListEntry(NameAndId user) {
    }

    public UserBanListEntry(NameAndId user, Date created, String source, Date expires, String reason) {
    }

    public UserBanListEntry(JsonObject object) {
    }

    protected void serialize(JsonObject object) {
        throw Unimplemented.forMember("net/minecraft/server/players/UserBanListEntry.serialize:(Lcom/google/gson/JsonObject;)V");
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/server/players/UserBanListEntry.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public UserBanListEntry() {
    }
}
