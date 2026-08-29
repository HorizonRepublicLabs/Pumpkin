package net.minecraft.client.telemetry;

import java.time.Duration;
import java.util.UUID;
import dev.pumpkin.shim.Unimplemented;

public class WorldSessionTelemetryManager {

    public WorldSessionTelemetryManager(TelemetryEventSender eventSender, boolean newWorld, Duration worldLoadDuration, String minigameName, UUID sessionId) {
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/WorldSessionTelemetryManager.tick:()V");
    }

    public WorldSessionTelemetryManager() {
    }
}
