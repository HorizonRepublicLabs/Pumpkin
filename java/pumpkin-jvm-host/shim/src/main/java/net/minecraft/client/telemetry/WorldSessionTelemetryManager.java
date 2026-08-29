package net.minecraft.client.telemetry;

import java.time.Duration;
import java.util.UUID;
import dev.pumpkin.shim.Unimplemented;

public class WorldSessionTelemetryManager {

    public WorldSessionTelemetryManager(TelemetryEventSender eventSender, boolean newWorld, Duration worldLoadDuration, String minigameName, UUID sessionId) {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/WorldSessionTelemetryManager.<init>:(Lnet/minecraft/client/telemetry/TelemetryEventSender;ZLjava/time/Duration;Ljava/lang/String;Ljava/util/UUID;)V");
    }

    public void tick() {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/WorldSessionTelemetryManager.tick:()V");
    }

    protected WorldSessionTelemetryManager() {
    }
}
