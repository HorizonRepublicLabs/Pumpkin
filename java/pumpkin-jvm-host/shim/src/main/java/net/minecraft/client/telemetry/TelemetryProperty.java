package net.minecraft.client.telemetry;

import com.mojang.authlib.minecraft.TelemetryPropertyContainer;
import com.mojang.serialization.Codec;
import net.minecraft.util.StringRepresentable;
import dev.pumpkin.shim.Unimplemented;

public record TelemetryProperty<T>(String id, String exportKey, Codec<T> codec, TelemetryProperty.Exporter<T> exporter) {

    public static <T> TelemetryProperty<T> create(String id, String exportKey, Codec<T> codec, TelemetryProperty.Exporter<T> exporter) {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryProperty.create:(Ljava/lang/String;Ljava/lang/String;Lcom/mojang/serialization/Codec;Lnet/minecraft/client/telemetry/TelemetryProperty$Exporter;)Lnet/minecraft/client/telemetry/TelemetryProperty;");
    }

    public String toString() {
        throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryProperty.toString:()Ljava/lang/String;");
    }

    public interface Exporter<T> {

        void apply(TelemetryPropertyContainer output, String key, T value);
    }

    public enum GameMode implements StringRepresentable {

        SURVIVAL, CREATIVE, ADVENTURE, SPECTATOR, HARDCORE;

        public int id() {
            throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryProperty$GameMode.id:()I");
        }

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryProperty$GameMode.getSerializedName:()Ljava/lang/String;");
        }
    }

    public enum ServerType implements StringRepresentable {

        REALM, LOCAL, OTHER;

        public String getSerializedName() {
            throw Unimplemented.forMember("net/minecraft/client/telemetry/TelemetryProperty$ServerType.getSerializedName:()Ljava/lang/String;");
        }
    }
}
