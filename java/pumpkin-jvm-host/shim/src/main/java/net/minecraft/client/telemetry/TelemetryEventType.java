package net.minecraft.client.telemetry;

import com.mojang.serialization.MapCodec;
import java.util.List;
import net.minecraft.network.chat.MutableComponent;
import dev.pumpkin.shim.Unimplemented;

public class TelemetryEventType {

    private TelemetryEventType(String id, String exportKey, List<TelemetryProperty<?>> properties, boolean isOptIn) {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryEventType.<init>:(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Z)V");
    }

    public String id() {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryEventType.id:()Ljava/lang/String;");
    }

    public MapCodec<TelemetryEventInstance> codec() {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryEventType.codec:()Lcom/mojang/serialization/MapCodec;");
    }

    public <T> boolean contains(TelemetryProperty<T> property) {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryEventType.contains:(Lnet/minecraft/client/telemetry/TelemetryProperty;)Z");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryEventType.toString:()Ljava/lang/String;");
    }

    public MutableComponent description() {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryEventType.description:()Lnet/minecraft/network/chat/MutableComponent;");
    }

    public static class Builder {

        private Builder(String id, String exportKey) {
            throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryEventType$Builder.<init>:(Ljava/lang/String;Ljava/lang/String;)V");
        }

        public Builder() {
        }
    }

    public TelemetryEventType() {
    }
}
