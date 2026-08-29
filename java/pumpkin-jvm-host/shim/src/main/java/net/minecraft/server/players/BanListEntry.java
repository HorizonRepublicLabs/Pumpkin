package net.minecraft.server.players;

import com.google.gson.JsonObject;
import java.util.Date;
import net.minecraft.network.chat.Component;
import dev.pumpkin.shim.Unimplemented;

public abstract class BanListEntry<T> extends StoredUserEntry<T> {

    public BanListEntry(T user, Date created, String source, Date expires, String reason) {
        throw Unimplemented.forMember("net/minecraft/server/players/BanListEntry.<init>:(Ljava/lang/Object;Ljava/util/Date;Ljava/lang/String;Ljava/util/Date;Ljava/lang/String;)V");
    }

    protected BanListEntry(T user, JsonObject object) {
        throw Unimplemented.forMember("net/minecraft/server/players/BanListEntry.<init>:(Ljava/lang/Object;Lcom/google/gson/JsonObject;)V");
    }

    public String getSource() {
        throw Unimplemented.forMember("net/minecraft/server/players/BanListEntry.getSource:()Ljava/lang/String;");
    }

    public abstract Component getDisplayName();

    public boolean hasExpired() {
        throw Unimplemented.forMember("net/minecraft/server/players/BanListEntry.hasExpired:()Z");
    }

    protected void serialize(JsonObject object) {
        throw Unimplemented.forMember("net/minecraft/server/players/BanListEntry.serialize:(Lcom/google/gson/JsonObject;)V");
    }

    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/server/players/BanListEntry.equals:(Ljava/lang/Object;)Z");
    }

    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/server/players/BanListEntry.hashCode:()I");
    }

    protected BanListEntry() {
    }
}
