package net.minecraft.util.debug;

import net.minecraft.server.level.ServerLevel;

public interface DebugValueSource {

    void registerDebugValues(ServerLevel level, DebugValueSource.Registration registration);

    interface Registration {

        <T> void register(DebugSubscription<T> subscription, DebugValueSource.ValueGetter<T> getter);
    }

    interface ValueGetter<T> {

        T get();
    }
}
