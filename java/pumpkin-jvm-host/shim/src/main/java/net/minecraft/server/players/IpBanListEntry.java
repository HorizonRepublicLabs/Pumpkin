package net.minecraft.server.players;

import com.google.gson.JsonObject;
import java.util.Date;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public class IpBanListEntry extends BanListEntry<String> {

    public IpBanListEntry(String address) {
    }

    public IpBanListEntry(String address, Date created, String source, Date expires, String reason) {
    }

    public Component getDisplayName() {
        throw Unimplemented.forMember("net/minecraft/server/players/IpBanListEntry.getDisplayName:()Lnet/minecraft/network/chat/Component;");
    }

    public IpBanListEntry(JsonObject object) {
    }

    protected void serialize(JsonObject object) {
        throw Unimplemented.forMember("net/minecraft/server/players/IpBanListEntry.serialize:(Lcom/google/gson/JsonObject;)V");
    }

    public IpBanListEntry() {
    }
}
