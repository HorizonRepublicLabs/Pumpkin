package net.minecraft.client.telemetry;

import java.util.Map;
import dev.pumpkin.shim.Unimplemented;

public class TelemetryPropertyMap {

    private TelemetryPropertyMap(Map<TelemetryProperty<?>, Object> entries) {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryPropertyMap.<init>:(Ljava/util/Map;)V");
    }

    public <T> T get(TelemetryProperty<T> property) {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryPropertyMap.get:(Lnet/minecraft/client/telemetry/TelemetryProperty;)Ljava/lang/Object;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryPropertyMap.toString:()Ljava/lang/String;");
    }

    public static class Builder {

        protected Builder() {
            throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryPropertyMap$Builder.<init>:()V");
        }

        public TelemetryPropertyMap build() {
            throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryPropertyMap$Builder.build:()Lnet/minecraft/client/telemetry/TelemetryPropertyMap;");
        }
    }

    public TelemetryPropertyMap() {
    }
}
