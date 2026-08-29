package net.minecraft.server.players;

import com.google.gson.JsonObject;

public abstract class StoredUserEntry<T> {

    public StoredUserEntry(T user) {
    }

    protected abstract void serialize(final JsonObject object);

    public StoredUserEntry() {
    }
}
