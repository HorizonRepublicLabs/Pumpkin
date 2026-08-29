package net.minecraft.client.telemetry;

import java.util.function.Consumer;
import dev.pumpkin.shim.Unimplemented;

public interface TelemetryEventSender {

    default TelemetryEventSender decorate(Consumer<TelemetryPropertyMap.Builder> decorator) {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryEventSender.decorate:(Ljava/util/function/Consumer;)Lnet/minecraft/client/telemetry/TelemetryEventSender;");
    }

    void send(TelemetryEventType type, Consumer<TelemetryPropertyMap.Builder> buildFunction);
}
