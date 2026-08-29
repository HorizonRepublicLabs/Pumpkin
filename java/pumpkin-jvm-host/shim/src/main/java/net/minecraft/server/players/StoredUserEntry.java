package net.minecraft.server.players;

import com.google.gson.JsonObject;
import dev.pumpkin.shim.Unimplemented;

public abstract class StoredUserEntry<T> {

    public StoredUserEntry(T user) {
        throw Unimplemented.forMember("net/minecraft/server/players/StoredUserEntry.<init>:(Ljava/lang/Object;)V");
    }

    protected abstract void serialize(final JsonObject object);

    protected StoredUserEntry() {
    }
}
